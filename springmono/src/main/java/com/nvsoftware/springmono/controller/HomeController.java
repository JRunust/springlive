package com.nvsoftware.springmono.controller;

import com.nvsoftware.springmono.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/user/{id}/{username}")
    public User userByPathVariable(@PathVariable("id") String id, @PathVariable("username") String username){
        User user = new User();
        user.setId(id);
        user.setName(username);
        user.setEmail("user@nvsoftware.com");
        return user;
    }

    
}
