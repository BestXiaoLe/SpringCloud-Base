package com.bestxiaole.system.controller;

import com.bestxiaole.server.pojo.User;
import com.bestxiaole.server.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/test")
public class TestContrller {

    @Resource
    private UserService userService;

    @Value("${myname}")
    private String my_name;

    @RequestMapping("/hello")
    public String test() {
        return "System Hello~";
    }

    @RequestMapping("/myname")
    public String myname() {
        return my_name;
    }

    @RequestMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GlobalTransactional(timeoutMills = 30000, name = "spring-cloud-system-test")
    @RequestMapping("/buybook")
    public String buybook() {
        User user = new User();
        user.setId(1);
        userService.userBuyBook(user);
        return "success";
    }

}
