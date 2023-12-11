package com.hirenseeks.hirenseeks.user;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/allUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/signup")
    public Map<String, Object> signUp(@RequestBody User newUser) {
        System.out.println(newUser.toString());
        return userService.userSignUp(newUser);
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(@RequestBody String userName, String password) {
        System.out.println("Hello");
        System.out.println(userName + " " + password);
        // sessionService.loginUser(session.getId(), userName, password);

        return "Login successful";
    }

    // @GetMapping("/user")
    // public Map<String, Object> getLoggedInUser() {
    // Map<String, Object> response = new HashMap<>();
    // response.put("User", userService.getCurrentUsername());
    // return response;
    // }
    @GetMapping("/user")
    public String user() {
        return "";
    }

    @GetMapping(path = "/user/{userName}")
    public Map<String, Object> getUser(@PathVariable("userName") String userName) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("userData", userService.getUsers(userName));
        return response;
    }

    @PostMapping(path = "/update")
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> response = userService.updateUser(user);
        return response;
    }

    @GetMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

}
