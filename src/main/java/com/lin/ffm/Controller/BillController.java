package com.lin.ffm.Controller;

import com.lin.ffm.pojo.Bill;
import com.lin.ffm.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BillController {

    @Autowired
    private BillService billService;


    @RequestMapping("bill")
    public String test(Model model){
        List<Bill> bills = billService.findAllBill();
        System.out.println(bills.toString());
        System.out.println("=============================================================");
        System.out.println(billService.findAllBill().toString());
        model.addAttribute("bills",bills);
        return "test";
    }

    @RequestMapping("changeBill")
    public void changeBill(Bill bill){
        billService.changeBill(bill);
    }
}
