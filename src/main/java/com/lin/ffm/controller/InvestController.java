package com.lin.ffm.controller;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Invest;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.InvestService;
import com.lin.ffm.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class InvestController {

    @Autowired
    InvestService investService;

    @RequestMapping("/invest")
    public String invest(Invest invest, Model model, Boolean me, Integer pageNum, Integer pageSize, HttpSession session)  {
        User u = (User) session.getAttribute("USER_SESSION");
        if (me == null){
            me = false;
        }
        if (me){
            invest.setUserId(u.getId());
        }
        if (Tool.isNumber(invest.getName())){
            invest.setMoney(Double.parseDouble(invest.getName()));
        }
        invest.setUser(u);
        PageInfo<Invest> page = investService.findInvest(invest,pageNum,pageSize);
        model.addAttribute("page",page);
        model.addAttribute("search",invest);
        model.addAttribute("me",me);
        return "client/invest";
    }

    @RequestMapping("/findInvestById")
    @ResponseBody
    public Invest findInvestById(int id){
        return investService.findInvestById(id);
    }

    @ResponseBody
    @RequestMapping("/editInvest")
    public String editInvest(Invest invest){
        Boolean b = timeCompare(invest.getStartDate(),invest.getEndDate());
        if (b) {
            int i = investService.editInvest(invest);
            if (i > 0) {
                return "修改成功！";
            } else {
                return "修改失败！";
            }
        }
            return "结束日期不能小于开始日期!";
    }

    @ResponseBody
    @RequestMapping("/deleteInvest")
    public String deleteLoan(int id){
        int i = investService.deleteInvest(id);
        if (i>0){
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }

    @ResponseBody
    @RequestMapping("/addInvest")
    public String addInvest(Invest invest,HttpSession session) throws ParseException {
        User u = (User) session.getAttribute("USER_SESSION");
        invest.setUserId(u.getId());
        Boolean b = timeCompare(invest.getStartDate(),invest.getEndDate());
        if(invest.getIncome() == null  || invest.getIncome() == 0.0){
            invest.setIncome(Tool.getMonthSpace(invest.getEndDate(),invest.getStartDate())*(invest.getRate()*invest.getMoney()/12));
        }
        if (b) {
            int i = investService.addInvest(invest);
            if (i > 0) {
                return "添加成功！";
            } else {
                return "添加失败！";
            }
        }
        return "结束日期不能小于开始日期!";
    }

    public Boolean timeCompare(String startDate,String endDate){
        int i = startDate.compareTo(endDate);
        if (i>0){
            return false;
        }else {
            return true;
        }
    }

    //预期收益 = 投资本金 × 年化收益率 × 投资期限(天) / 365

}
