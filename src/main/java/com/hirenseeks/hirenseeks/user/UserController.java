package com.hirenseeks.hirenseeks.user;

import java.security.Principal;
import java.util.*;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/login")
    // public String login(@RequestParam String userName, @RequestParam String
    // password) {
    public String login(Principal p) {
        // Principal p;
        System.out.println(p);
        return "" + p.getName();
        // return userService.login(userName, password);
    }

    @GetMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

}
