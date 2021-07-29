package com.example.interceptor.service;

import com.example.interceptor.dto.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImple implements UserService {
    @Override
    public void login(HttpSession session, User user) {
        session.setAttribute("userId", user.getUserId());
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("userId");
    }

    @Override
    public User getUser(HttpSession session) {
        User user = new User();
        user.setUserId(session.getAttribute("userId").toString());

        return user;
    }
}
