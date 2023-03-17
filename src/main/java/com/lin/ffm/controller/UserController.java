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


    @RequestMapping("/login")
    public String login(User user, Model model , HttpSession session){
        User u = userService.login(user);
        boolean flag = false;
        if (u != null){
            try {
                flag = MyMD5Util.validPassword(user.getPassword(),u.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            model.addAttribute("msg","用户不存在");
            return "login";
        }
        if (flag){
            session.setAttribute("USER_SESSION",u);
            if (u.getRole().equals("admin")){
                return "redirect:/admin";
            }else if (u.getRole().equals("户主")){
                return null;
            }
            return "client/main";
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

    @ResponseBody
    @RequestMapping("/register")
    public String register(User user) {
        int result = 0;
        String pwd = null;
        User u = userService.login(user);
        Message message = new Message();
        message.setFirstname("暂无信息，点我添加");
        message.setLastname("暂无信息，点我添加");
        message.setAddress("暂无信息，点我添加");
        message.setEmail("暂无信息，点我添加");
        message.setMsg("暂无信息，点我添加");
        Message m =messageService.addMessage(message);
        if (u != null) {
            return "用户名已存在，请重新输入";
        } else {
            try {
                //加密
                pwd = encryptionPwd("123");
                user.setPassword(pwd);
                user.setMsgId(m.getId());
                result = userService.register(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result>0){
            return "添加成功";
        }else {
            return "未知原因，添加失败";
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

    @RequestMapping("/admin")
    public String admin(User user,Integer pageNum,Integer pageSize,Model model){
        PageInfo<User> page = userService.findAllUser(user,pageNum,pageSize);
        model.addAttribute("page",page);
        model.addAttribute("search",user);
        return "admin/client";
    }

    @ResponseBody
    @RequestMapping("/findUserById")
    public User findUserById_edit(int id){
        return userService.findUserById(id);
    }

    @ResponseBody
    @RequestMapping("/editUser")
    public String editUser(User user){
        int i = userService.editUser(user);
        if (i>0){
            return "修改成功！";
        }else {
            return "修改失败！";
        }
    }

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
    @RequestMapping("saveAvatar")
    public String changeImg(@RequestParam("saveAvatar") MultipartFile file, HttpSession session) throws IOException{
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
}
