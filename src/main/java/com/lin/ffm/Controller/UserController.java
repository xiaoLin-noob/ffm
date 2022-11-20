package com.lin.ffm.Controller;


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
        if (u != null){
            session.setAttribute("USER_SESSION",u.getUsername());
            if (u.getRole() .equals("admin")){
                return "admin/main";
            }
            return "client/success";
        }
        model.addAttribute("msg","用户名或密码错误");
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


}
