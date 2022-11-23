package com.lin.ffm.controller;


import com.lin.ffm.util.MyMD5Util;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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

    @RequestMapping("/updatePassword")
    public String updatePassword(User user){
        return null;
    }

    @RequestMapping("/register")
    public String register(User user) {
        String encryptedPwd = null;
        int result = 0;
        boolean flag = false;
        User u = userService.login(user);
        if (u != null) {
            return "用户已存在";
        } else {
            try {
                //加密
                encryptedPwd = MyMD5Util.getEncryptedPwd(user.getPassword());
                user.setPassword(encryptedPwd);
                result = userService.register(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result>0){
            return null;
        }else {
            return null;
        }
    }
}
