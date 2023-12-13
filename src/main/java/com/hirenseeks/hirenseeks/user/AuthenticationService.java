package com.hirenseeks.hirenseeks.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public Map<String, Object> authenticate(
            User userData, HttpServletRequest request) {
        // Implement your authentication logic (e.g., check against database)
        User userOptional = new User();
        boolean flag = false;
        Map<String, Object> response = new HashMap<>();
        if (userData.getUserName() != null && userRepository.existsByUserName(userData.getUserName())) {
            userOptional = userRepository.findUserByUserName(userData.getUserName());
            flag = true;
        } else if (userData.getEmail() != null && userRepository.existsByEmail(userData.getEmail())) {
            userOptional = userRepository.findUserByEmail(userData.getEmail());
            flag = true;
        } else if (userData.getContactNumber() != null && userRepository.existsByEmail(userData.getContactNumber())) {
            userOptional = userRepository.findUserByContactNumber(userData.getContactNumber());
            flag = true;
        } else {
            flag = false;
        }

        if (flag && Encrypt.verifyPassword(userData.getPassword(), userOptional.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", userOptional.getUserName());
            response.put("success", true);
            userOptional.setPassword(null);
            response.put("User", userService.getUsers(userOptional.getUserName()));
        } else {
            response.put("success", false);
            response.put("error", "Username or Password Wrong");
        }

        return response;
    }
}