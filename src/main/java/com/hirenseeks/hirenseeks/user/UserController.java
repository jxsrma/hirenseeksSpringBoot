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
import jakarta.servlet.http.HttpSession;

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
        System.out.println(newUser.toString());
        return userService.userSignUp(newUser);
    }

    @CrossOrigin
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User userData, HttpServletRequest request) {
        // Map<String, Object> response = new HashMap<>();
        return authenticationService.authenticate(userData, request);

    }

    @GetMapping("/user")
    public Map<String, Object> user(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                response.put("User", username);
            }
        } else {
            response.put("User", null);
        }
        return response;
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

    @GetMapping("/logouts")
    public Map<String, Object> logout(HttpServletRequest request) {
        // Invalidate the session to log out the user
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            // return "Logout successful!";
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("User", "None: Logged Out");

        return response;
    }

}
