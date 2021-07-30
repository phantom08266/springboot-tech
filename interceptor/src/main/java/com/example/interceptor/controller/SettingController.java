package com.example.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingController {

    @GetMapping
    public String getSettings() {
        System.out.println("get Settings");
        return "controller getSettings";
    }

}
