package com.lin.ffm.controller;


import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Loan;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;


    @RequestMapping("loan")
    public String loan(Loan loan, Model model, Integer pageNum, Integer pageSize, HttpSession session){
        User u = (User) session.getAttribute("USER_SESSION");
        loan.setUserId(u.getId());
        PageInfo<Loan> page = loanService.findLoans(loan,pageNum,pageSize);
        model.addAttribute("page",page);
        model.addAttribute("search",loan);
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
    public String addLoan(Loan loan,HttpSession session){
        User u = (User) session.getAttribute("USER_SESSION");
        loan.setUserId(u.getId());
        int i = loanService.addLoan(loan);
        if (i>0){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
}