package com.lin.ffm.controller;


import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Loan;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.LoanService;
import com.lin.ffm.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.regex.Pattern;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;


    @RequestMapping("loan")
    public String loan(Loan loan,boolean me, Model model, Integer pageNum, Integer pageSize, HttpSession session){
        if (Tool.isNumber(loan.getWhere())){
            loan.setMoney(Double.parseDouble(loan.getWhere()));
        }

        User u = (User) session.getAttribute("USER_SESSION");
        loan.setUser(u);
        if (me){
            loan.setUserId(u.getId());
        }
        PageInfo<Loan> page = loanService.findLoans(loan,pageNum,pageSize);
        model.addAttribute("page",page);
        model.addAttribute("search",loan);
        model.addAttribute("me",me);
        return "client/loan";
    }
    @ResponseBody
    @RequestMapping("findLoanById")
    public Loan findLoanById(int id){
        return loanService.findLoanById(id);
    }

    @ResponseBody
    @RequestMapping("editLoan")
    public String editLoan(Loan loan){
        int i = loanService.editLoan(loan);
        if (i>0){
            return "修改成功！";
        }else {
            return "修改失败！";
        }
    }

    @ResponseBody
    @RequestMapping("deleteLoan")
    public String deleteLoan(int id){
        int i = loanService.deleteLoan(id);
        if (i>0){
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }

    @ResponseBody
    @RequestMapping("addLoan")
    public String addLoan(Loan loan,HttpSession session) throws ParseException {
        User u = (User) session.getAttribute("USER_SESSION");
        loan.setUserId(u.getId());
        if(loan.getPayBack() == null || loan.getPayBack() == 0.0){
            loan.setPayBack(Tool.getMonthSpace(loan.getEndDate(),loan.getStartDate())*(loan.getRates()*loan.getMoney()/12));
        }
        int i = loanService.addLoan(loan);
        if (i>0){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }


    //计算贷款每个月的利息，公式为：(贷款本金 x 年利率) / 12
    //计算预期还款总额，公式为：每个月应还总额 x 还款期数
}