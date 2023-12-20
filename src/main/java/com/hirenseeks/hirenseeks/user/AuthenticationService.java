package com.hirenseeks.hirenseeks.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenseeks.hirenseeks.response.CustomResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    CustomResponse customResponse = new CustomResponse();

    public Map<String, Object> authenticate(
            User userData, HttpServletRequest request) {
        User mainUser = new User();
        boolean flag = false;
        if (userData.getUserName() != null && userRepository.existsByUserName(userData.getUserName())) {
            mainUser = userRepository.findUserByUserName(userData.getUserName());
            flag = true;
        } else if (userData.getEmail() != null && userRepository.existsByEmail(userData.getEmail())) {
            mainUser = userRepository.findUserByEmail(userData.getEmail());
            flag = true;
        } else if (userData.getContactNumber() != null && userRepository.existsByEmail(userData.getContactNumber())) {
            mainUser = userRepository.findUserByContactNumber(userData.getContactNumber());
            flag = true;
        } else {
            flag = false;
        }

        if (flag && Encrypt.verifyPassword(userData.getPassword(), mainUser.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", mainUser.getUserName());

            mainUser.setLastLogin(new Date());
            userRepository.save(mainUser);
            mainUser.setPassword(null);

            return customResponse.returnSuccessTrueResponse("userData", userService.getUsers(mainUser.getUserName()));
        } else {
            return customResponse.returnSuccessFalseResponse("Username or Password Wrong");
        }
    }
}
