package com.example.interceptor.controller;

import com.example.interceptor.annotation.Auth;
import org.springframework.web.bind.annotation.*;

@Auth
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @GetMapping
    public String getProfile(){
        System.out.println("controller get profile");
        return "controller getProfile";
    }
}
