package com.lin.ffm.controller;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.*;
import com.lin.ffm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private BillService billService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private InvestService investService;

    @Autowired
    private UserService userService;

    @RequestMapping("/allBill")
    public String allBill(Model model,Integer pageNum,Integer pageSize){
        PageInfo<Bill> page = billService.ALlBill(pageNum,pageSize);
        model.addAttribute("page",page);
        return "admin/allBill";
    }

    @RequestMapping("/allUser")
    public String allUser(Model model,Integer pageNum,Integer pageSize,User user){
        PageInfo<User> page = userService.findAllUser(user,pageNum,pageSize);
        model.addAttribute("page",page);
        return "admin/user";
    }

    @RequestMapping("/allLoan")
    public String allLoan(Model model,Integer pageNum,Integer pageSize,Loan loan){
        PageInfo<Loan> page = loanService.loans(pageNum,pageSize);
        model.addAttribute("page",page);
        return "admin/loan";
    }

    @RequestMapping("/allInvest")
    public String allInvest(Model model,Integer pageNum,Integer pageSize,Invest invest){
        PageInfo<Invest> page = investService.invests(pageNum,pageSize);
        model.addAttribute("page",page);
        return "admin/invest";
    }

    @RequestMapping("/allItems")
    public String allItem(Model model,Integer pageNum,Integer pageSize){
        PageInfo<Item> page = itemService.items(pageNum,pageSize);
        model.addAttribute("page",page);
        return "admin/item";
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(int id,int status){
        User user = new User();
        user.setId(id);
        switch (status){
            case 0:
                user.setStatus(1);
                break ;
            case 1:
                user.setStatus(0);
                break;
        }
        int i =userService.editUser(user);
        if (i>0){
            return "成功！";
        }else {
            return "失败! ";
        }
    }

//    @RequestMapping("/freeUser")
//    @ResponseBody
//    public String freeUser(int id){
//        User user = new User();
//        user.setId(id);
//        user.setStatus(1);
//        int i =userService.editUser(user);
//        if (i>0){
//            return "封禁成功！";
//        }else {
//            return "封禁失败";
//        }
//    }
}
