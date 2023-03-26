package com.lin.ffm;

import com.lin.ffm.dao.BillDao;
import com.lin.ffm.dao.ItemDao;
import com.lin.ffm.dao.LoanDao;
import com.lin.ffm.dao.UserDao;
import com.lin.ffm.pojo.Item;
import com.lin.ffm.pojo.Loan;
import com.lin.ffm.pojo.Message;
import com.lin.ffm.repository.MessageRepository;
import com.lin.ffm.service.BillService;
import com.lin.ffm.service.ItemService;
import com.lin.ffm.service.LoanService;
import com.lin.ffm.util.MyMD5Util;
import com.lin.ffm.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class FamilyFinancialManagementApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    BillDao billDao;

    @Autowired
    MessageRepository messageRepository;

    @Test
    void contextLoads() {
        String encryptedPwd = null;
        int result = 0;
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123456");
        user.setRole("user");
        user.setHouseId(1);
        try {
            //加密
            encryptedPwd = MyMD5Util.getEncryptedPwd(user.getPassword());
            user.setPassword(encryptedPwd);
            result = userDao.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Autowired
    BillService billService;

    @Test
    void testSession(){
        Message message = new Message();
        message.setId(3);
        message.setFirstname("333");
        message.setLastname("333");
        message.setEmail("333");
        message.setAddress("333");
        message.setMsg("西红柿");
        System.out.println(messageRepository.save(message));
    }




    @Test
    public void test(){
        String ts = "123";
        String test = encryptionPwd(ts);
        System.out.println(test);
    }

    public String encryptionPwd(String pwd){
        String encryptedPwd = null;
        try {
            //加密
            encryptedPwd = MyMD5Util.getEncryptedPwd(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPwd;
    }

    @Autowired
    LoanService loanService;

    @Autowired
    LoanDao loanDao;

    @Autowired
    ItemService itemDao;

    @Test
    public void sqlTest(){

        System.out.println(itemDao.readItem(3));
    }

    @Test
    public void timeTest(){
        System.out.println(new Date());
    }

}
