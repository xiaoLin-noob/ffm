package com.lin.ffm.controller;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Bill;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class BillController {

    @Autowired
    private BillService billService;


    @RequestMapping("bill")
    public String test( Bill bill,Model model,Integer pageNum,Integer pageSize, HttpSession session){
        User u = (User) session.getAttribute("USER_SESSION");
        bill.setUserId(u.getId());
        PageInfo<Bill> page = billService.findBills(bill,pageNum,pageSize);
        model.addAttribute("page",page);
        return "client/bill";
    }

    @RequestMapping("editBill")
    public void editBill(Bill bill){
        int i = billService.editBill(bill);
    }
}
