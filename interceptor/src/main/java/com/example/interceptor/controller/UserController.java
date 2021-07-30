package com.example.interceptor.controller;

import com.example.interceptor.dto.User;
import com.example.interceptor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public void login(@RequestBody User user, HttpSession session){
        userService.login(session, user);
    }

    @DeleteMapping("logout")
    public void logout(HttpSession session){
        userService.logout(session);
    }

    @GetMapping
    public User getUser(HttpSession session){
        return userService.getUser(session);
    }

}
