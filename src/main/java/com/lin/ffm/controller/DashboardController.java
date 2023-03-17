package com.lin.ffm.controller;

import com.lin.ffm.pojo.User;
import com.lin.ffm.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private BillService billService;

    @RequestMapping("/dashboard")
    public String main(HttpSession session,Integer selectMonth, Model model){
//        if(month == 0){
//            Calendar now = Calendar.getInstance();
//            month = now.get(Calendar.MONTH);
//        }
        User u = (User) session.getAttribute("USER_SESSION");
        List<Double> outYearMoney = yearBill(u.getId(),1);
        List<Double> inYearMoney = yearBill(u.getId(),0);
        model.addAttribute("byout",outYearMoney);
        model.addAttribute("byin",inYearMoney);
        if (selectMonth == null){
            Calendar now = Calendar.getInstance();
            selectMonth = now.get(Calendar.MONTH);
            model.addAttribute("bmo", outYearMoney.get(selectMonth));
            model.addAttribute("bmi", inYearMoney.get(selectMonth));
        }else{
            model.addAttribute("bmo", outYearMoney.get(selectMonth -1));
            model.addAttribute("bmi", inYearMoney.get(selectMonth -1));
        }

        return "client/dashboard";
    }

    public List<Double> yearBill(int id, int type){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        List<Double> out =new ArrayList<>();
        for (int i=1;i<=12;i++){
            out.add(billService.outYear(id,year,i,type));
        }
        return out;
    }







}
