package com.lin.ffm;

import com.lin.ffm.dao.UserDao;
import com.lin.ffm.util.MyMD5Util;
import com.lin.ffm.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

@SpringBootTest
class FamilyFinancialManagementApplicationTests {
    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        String encryptedPwd = null;
        int result = 0;
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123456");
        user.setRole("user");
        user.setHouseId("1");
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

    @Test
    void testSession(){




    }

}
