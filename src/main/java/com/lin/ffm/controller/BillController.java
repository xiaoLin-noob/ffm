package com.lin.ffm.controller;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Bill;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class BillController {

    @Autowired
    private BillService billService;


    @RequestMapping("bill")
    public String Bill( Bill bill,Boolean me,Model model,Integer pageNum,Integer pageSize, HttpSession session){
        User u = (User) session.getAttribute("USER_SESSION");
        if (me == null){
            me = false;
        }
        if (me){
            bill.setUserId(u.getId());
        }
        if (u.getHouseId() == 0){
            bill.setUserId(u.getId());
        }
        bill.setUser(u);
        PageInfo<Bill> page = billService.findBills(bill,pageNum,pageSize);
        model.addAttribute("page",page);
        model.addAttribute("search",bill);
        model.addAttribute("me",me);
        return "client/bill";
    }

    @ResponseBody
    @RequestMapping("findBillById")
    public Bill findBillById(int id){
        return billService.findBillById(id);
    }

    @ResponseBody
    @RequestMapping("editBill")
    public String editBill(Bill bill){
        int i = billService.editBill(bill);
        if (i>0){
            return "修改成功！";
        }else {
            return "修改失败！";
        }
    }

    @ResponseBody
    @RequestMapping("deleteBill")
    public String deleteBill(int id){
        int i = billService.deleteBill(id);
        if (i>0){
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }

    @ResponseBody
    @RequestMapping("addBill")
    public String addBill(Bill bill,HttpSession session){
        User u = (User) session.getAttribute("USER_SESSION");
        bill.setUserId(u.getId());
        int i = billService.addBill(bill);
        if (i>0){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
}
