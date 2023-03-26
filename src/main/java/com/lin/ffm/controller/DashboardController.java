package com.lin.ffm.controller;

import com.lin.ffm.pojo.Invest;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.BillService;
import com.lin.ffm.service.InvestService;
import com.lin.ffm.service.LoanService;
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

    @Autowired
    private LoanService loanService;

    @Autowired
    private InvestService investService;

    @RequestMapping("/dashboard")
    public String main(HttpSession session,Integer selectMonth, Model model){
        User u = (User) session.getAttribute("USER_SESSION");
        List<Double> outYearMoney = yearBill(u.getId(),1);
        List<Double> inYearMoney = yearBill(u.getId(),0);
        List<Double> loanYear = yearLoan(u.getId());
        List<Double> investYear = yearInvest(u.getId());
        model.addAttribute("billOfYearOut",outYearMoney);
        model.addAttribute("billOfYearIn",inYearMoney);
        model.addAttribute("lianOfYear",loanYear);
        model.addAttribute("investOfYear",investYear);
        if (selectMonth == null){
            Calendar now = Calendar.getInstance();
            selectMonth = now.get(Calendar.MONTH);
            model.addAttribute("bmo", outYearMoney.get(selectMonth));
            model.addAttribute("bmi", inYearMoney.get(selectMonth));
            model.addAttribute("monthLoan",loanYear.get(selectMonth));
            model.addAttribute("monthInvest",investYear.get(selectMonth));
        }else{
            model.addAttribute("bmo", outYearMoney.get(selectMonth -1));
            model.addAttribute("bmi", inYearMoney.get(selectMonth -1));
            model.addAttribute("monthLoan", loanYear.get(selectMonth -1));
            model.addAttribute("monthInvest",investYear.get(selectMonth-1));

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

    public List<Double> yearLoan(int id){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        List<Double> l = new ArrayList<>();
        for (int i=1;i<=12;i++){
            l.add(loanService.LoanYear(id,year,i));
        }
        return l;
    }

    public List<Double> yearInvest(int id){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        List<Double> I = new ArrayList<>();
        for (int i=1;i<=12;i++){
            I.add(investService.InvestYear(id,year,i));
        }
        return I;
    }







}
