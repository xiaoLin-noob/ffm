package com.lin.ffm.controller;


import com.lin.ffm.pojo.Message;
import com.lin.ffm.service.MessageService;
import com.lin.ffm.util.MyMD5Util;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
            if (u.getRole() .equals("admin")){
                return "admin/main";
            }
            return "redirect:/dashboard";
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
                //return "修改成功";
                return "redirect:/user";
            }
//        }
        //return "失败，请过几分钟后重试。";
        return "redirect:/user";
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
        if (u != null) {
            return "用户已存在";
        } else {
            try {
                //加密
                pwd = encryptionPwd(user.getPassword());
                user.setPassword(pwd);
                result = userService.register(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result>0){
            return "注册成功";
        }else {
            return "注册失败";
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




}
