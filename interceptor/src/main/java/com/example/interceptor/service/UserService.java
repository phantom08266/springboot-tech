package com.example.interceptor.service;

import com.example.interceptor.dto.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    void login(HttpSession session, User user);

    void logout(HttpSession session);

    User getUser(HttpSession session);
}
