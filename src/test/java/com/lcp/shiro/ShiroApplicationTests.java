package com.lcp.shiro;

import com.lcp.shiro.pojo.User;
import com.lcp.shiro.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUsername("xixi");
        user.setPassword("123");
        user.setPerms("add");
        user.setRole("admin");
        System.out.println(userService.insertUser(user));
    }

}
