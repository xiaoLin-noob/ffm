package com.lin.ffm.controller;


import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Bill;
import com.lin.ffm.pojo.Message;
import com.lin.ffm.service.MessageService;
import com.lin.ffm.util.MyMD5Util;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;



    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }


    @RequestMapping("/login")
    public String login(User user, Model model , HttpSession session){
        if (user.getPassword() == null || user.getUsername() == null){
            model.addAttribute("msg","用户名或密码不能为空");
            return "login";
        }
        User u = userService.login(user);
        boolean flag = false;
        if (u != null){
            try {
                flag = MyMD5Util.validPassword(user.getPassword(),u.getPassword());//进行密码检验
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            model.addAttribute("msg","用户不存在");
            return "login";
        }
        if (flag){
            if (u.getStatus() == 0){
                session.setAttribute("USER_SESSION",u);
                switch (u.getRole()){
                    case "admin":
                        return "admin/main";
                    case "user":
                        return "client/main";
                }
            }else {
                model.addAttribute("msg","账户已被封禁");
            }
        }else {
            model.addAttribute("msg","密码错误");
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/toLogin";
    }

    @RequestMapping("/user")
    public String userMSG(HttpSession session,Model model){
        User user = (User) session.getAttribute("USER_SESSION");
        model.addAttribute("user",user);
        Message userMsg =  messageService.findMsgById(user.getMsgId());
        model.addAttribute("userMsg",userMsg);
        return "client/user";
    }

    @ResponseBody
    @RequestMapping("/editMessage")
    public String editMessage(Message msg,HttpSession session){
//        User user = (User) session.getAttribute("USER_SESSION");
//        if (user.getMsgId() == 0){
//            Message m =messageService.addMessage(msg);
//            if (m != null) {
//                user.setMsgId(m.getId());
//                userService.editUser(user);
//                //return "添加成功";
//                return "redirect:/user";
//            }
//        } else {
            Message m =messageService.editMessage(msg);
            if (m != null) {
                return "修改成功";
                //return "redirect:/user";
            }
//        }
        return "失败，请过几分钟后重试。";
        //return "redirect:/user";
    }

    @ResponseBody
    @RequestMapping("/editPassword")
    public String editPassword(String oldPwd, String pwd, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User u = (User) session.getAttribute("USER_SESSION");
        boolean flag = false;
        String newPwd = null;
        flag = MyMD5Util.validPassword(oldPwd,u.getPassword());
        if (flag){
            newPwd=encryptionPwd(pwd);
            u.setPassword(newPwd);
            userService.editUser(u);
            return "修改成功";
        }else {
            return "密码不正确";
        }
    }

    @RequestMapping("/register")
    public String register(User user,Model model) {
        int result = 0;
        String pwd = null;
        User u = userService.login(user);
        if (u != null) {
            model.addAttribute("msg","用户名已存在，请重新输入");
            return "register";
        } else {
            try {
                Message message = new Message();
                message.setFirstname("暂无信息，点我添加");
                message.setLastname("暂无信息，点我添加");
                message.setAddress("暂无信息，点我添加");
                message.setEmail("暂无信息，点我添加");
                message.setMsg("暂无信息，点我添加");
                Message m =messageService.addMessage(message);
                //加密
                pwd = encryptionPwd(user.getPassword());
                user.setPassword(pwd);
                user.setMsgId(m.getId());
                user.setHouseId(0);
                user.setRole("user");
                result = userService.register(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result>0){
            model.addAttribute("msg","注册成功");
            return "login";
        }else {
            model.addAttribute("msg","未知原因，注册失败，请稍后重试");
            return "register";
        }
    }

    //加密
    public String encryptionPwd(String pwd){
        String encryptedPwd = null;
        try {
            //加密
            encryptedPwd = MyMD5Util.getEncryptedPwd(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPwd;
    }

    @ResponseBody
    @RequestMapping("/findUserById")
    public User findUserById_edit(int id){
        return userService.findUserById(id);
    }

//    @ResponseBody
//    @RequestMapping("/editUser")
//    public String editUser(User user){
//        int i = userService.editUser(user);
//        if (i>0){
//            return "修改成功！";
//        }else {
//            return "修改失败！";
//        }
//    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        User u = userService.findUserById(id);
        messageService.deleteMessage(u.getMsgId());
        int i = userService.deleteUser(u.getId());
        if (i>0){
            return "操作成功";
        }else {
            return "操作失败";
        }
    }

    @ResponseBody
    @RequestMapping("editImg")
    public String changeImg(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException{
        User u = (User) session.getAttribute("USER_SESSION");
        if (file != null){
            if (Math.floor((double)file.getSize()/1024)>65.0){
                return "文件太大!";
            }

            InputStream inputStream = file.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

            int i = messageService.updateImg(u.getMsgId(),bytes);
            if (i>0){
                return "上传成功!";
            }else {
                return "上传失败!";
            }
        }
        return "未上传文件";
    }


    @RequestMapping("/notHome")
    @ResponseBody
    public List<User> findNotHomeUsers(){
        User u = new User();
        u.setHouseId(0);
        return userService.findUsers(u);
    }

}
