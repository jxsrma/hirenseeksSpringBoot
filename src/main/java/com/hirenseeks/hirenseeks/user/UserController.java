package com.hirenseeks.hirenseeks.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(path = "/allUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/signup")
    public Map<String, Object> signUp(@RequestBody User newUser) {
        return userService.userSignUp(newUser);
    }

    @CrossOrigin
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User userData, HttpServletRequest request) {
        return authenticationService.authenticate(userData, request);
    }

    @GetMapping("/userData")
    public Map<String, Object> getUserData(HttpServletRequest request) {
        return userService.userData(request);
    }

    @GetMapping("/user")
    public Map<String, Object> currUser(HttpServletRequest request) {
        return userService.currUser(request);
    }

    @GetMapping("/logouts")
    public Map<String, Object> logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @PostMapping(path = "/update")
    public Map<String, Object> updateUser(@RequestBody User user, HttpServletRequest request) {
        Map<String, Object> response = userService.updateUser(user, request);
        return response;
    }

    @GetMapping(path = "/user/{userName}")
    public Map<String, Object> getUser(@PathVariable("userName") String userName) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", userService.getUsers(userName));
        return response;
    }

}
