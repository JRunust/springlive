package com.nvsoftware.springmono.controller;

import com.nvsoftware.springmono.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "NVsoftware home";
    }
    @RequestMapping("/user")
    public User user(){
        User user = new User();
        user.setId("31001");
        user.setEmail("user@nvsoftware.com");
        user.setName("nvsoftware");
        return user;
    }
}
