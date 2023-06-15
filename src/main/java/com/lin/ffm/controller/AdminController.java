package com.lin.ffm.controller;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.*;
import com.lin.ffm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class AdminController {
    Calendar calendar = Calendar.getInstance();
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);
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

    @Autowired
    private AdminService adminService;

    @RequestMapping("/allFamilyDashboard")
    public String allFamilyDashboard(Model model){
        List<Integer>houseIds=adminService.findAllHouseId();
        List<Map<String,Object>>houseDatas = new ArrayList<>();
        for (int houseId:houseIds){
            Map<String,Object> houseData = new HashMap<>();
            List<Double> outBillOfHouse = null;
            outBillOfHouse = billOfHouse(houseId,1);
            List<Double> inBillOfHouse = null;
            inBillOfHouse = billOfHouse(houseId,0);
            List<Double> loanHouse = null;
            loanHouse = loanOfHouse(houseId);
            List<Double> investHouse = null;
            investHouse = investOfHouse(houseId);
            houseData.put("houseId",houseId);
            houseData.put("outBillOfHouse",outBillOfHouse);
            houseData.put("inBillOfHouse",inBillOfHouse);
            houseData.put("loanHouse",loanHouse);
            houseData.put("investHouse",investHouse);
            houseDatas.add(houseData);
        }
        model.addAttribute("houseDates",houseDatas);
        System.out.println(houseDatas);
        return "admin/allFamilyDashboard";
    }

    @RequestMapping("/allUserDashboard")
    public String allUserDashboard(Model model){
        List<Map<String,Object>>userDatas = new ArrayList<>();
        List<Integer> userIds=adminService.findAllUserId();
        for (int id:userIds){
            Map<String,Object> userData = new HashMap<>();
            List<Double> outYearMoney = null;
            outYearMoney = yearBill(id,1);
            List<Double> inYearMoney = null;
            inYearMoney = yearBill(id,0);
            List<Double> loanYear = null;
            loanYear = yearLoan(id);
            List<Double> investYear = null;
            investYear = yearInvest(id);
            userData.put("userId",id);
            userData.put("billOut",outYearMoney.get(month));
            userData.put("billIn",inYearMoney.get(month));
            userData.put("loan",loanYear.get(month));
            userData.put("invest",investYear.get(month));

            userData.put("lastBillOut",outYearMoney.get(month-1));
            userData.put("lastBillIn",inYearMoney.get(month-1));
            userData.put("lastLoan",loanYear.get(month-1));
            userData.put("lastInvest",investYear.get(month-1));
            userDatas.add(userData);
        }

        model.addAttribute("userDatas",userDatas);
        return "admin/allUserDashboard";
    }

    public List<Double> yearLoan(int id){
        List<Double> l = new ArrayList<>();
        for (int i=1;i<=12;i++){
            l.add(loanService.LoanYear(id,year,i));
        }
        return l;
    }

    public List<Double> yearInvest(int id){
        List<Double> I = new ArrayList<>();
        for (int i=1;i<=12;i++){
            I.add(investService.InvestYear(id,year,i));
        }
        return I;
    }

    public List<Double> yearBill(int id, int type){
        List<Double> out =new ArrayList<>();
        for (int i=1;i<=12;i++){
            out.add(billService.outYear(id,year,i,type));
        }
        return out;
    }

    public List<Double> billOfHouse(int houseId, int type){
        List<Double> out = new ArrayList<>();
        for (int i=1;i<=month;i++){
            out.add(adminService.billOfHouse(year,i,type,houseId));
        }
        return out;
    }

    public List<Double> investOfHouse(int houseId){
        List<Double> out = new ArrayList<>();
        for (int i=1;i<=month;i++){
            out.add(adminService.investOfHouse(year,i,houseId));
        }
        return out;
    }

    public List<Double> loanOfHouse(int houseId){
        List<Double> out = new ArrayList<>();
        for (int i=1;i<=month;i++){
            out.add(adminService.loanOfHouse(year,i,houseId));
        }
        return out;
    }




    @RequestMapping("/allUser")
    public String allUser(Model model,Integer pageNum,Integer pageSize,User user){
        PageInfo<User> page = userService.findAllUser(user,pageNum,pageSize);
        model.addAttribute("page",page);
        return "admin/user";
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


}
