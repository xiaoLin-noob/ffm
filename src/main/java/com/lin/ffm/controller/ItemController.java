package com.lin.ffm.controller;

import com.lin.ffm.pojo.Item;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.ItemService;
import com.lin.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;

    @RequestMapping("/inviteUser")
    @ResponseBody
    public String inviteUser(int id, HttpSession session){
        User u  = (User) session.getAttribute("USER_SESSION");
        Item item = new Item();
        item.setUserId(u.getId());
        item.setAddressId(id);
        item.setMsg("你的家人邀请你加入他的家庭组了！");
        item.setTime(getTime());
        item.setIsRead(0);
        if (itemService.invited(item)>0){
            return "你已邀请过该用户，请过几天后再邀请！";
        }else {
            int i = itemService.addItem(item);
            if (i>0){
                return "已通知该用户！";
            }
        }
        return "邀请失败！";
    }

    @RequestMapping("/allItem")
    @ResponseBody
    public List<Item> allItem(HttpSession session){
        User u  = (User) session.getAttribute("USER_SESSION");
        return itemService.allItem(u.getId());
    }


    @RequestMapping("/readItem")
    @ResponseBody
    public List<Item> readItem(int id){
        itemService.isReadItem(id);
        return itemService.readItem(id);
    }


    @RequestMapping("/joinFamily")
    @ResponseBody
    public String joinFamily(HttpSession session,int id){
        User u = userService.findUserById(itemService.findUserId(id));
        User user  = (User) session.getAttribute("USER_SESSION");
        if (u.getHouseId() == user.getHouseId()){
            return "您已加入该用户家庭组！";
        }
        user.setHouseId(u.getHouseId());
        int i = userService.editUser(user);
        if (i>0){
            return "success";
        }
        return "fail";
    }


    @ResponseBody
    @RequestMapping("/outFamily")
    public String outFamily(HttpSession session){
        User u  = (User) session.getAttribute("USER_SESSION");
        User user = new User();
        user.setId(u.getId());
        user.setHouseId(0);
        int i = userService.changeHouseId(user);
        if (i>0){
            return "操作成功!请重新登录";
        }
        return "操作失败";
    }

    @ResponseBody
    @RequestMapping("/findFamily")
    public List<User> findFamily(HttpSession session){
        User u  = (User) session.getAttribute("USER_SESSION");
        User user = new User();
        user.setHouseId(u.getHouseId());
        return userService.findFamily(user);

    }


    public String getTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
