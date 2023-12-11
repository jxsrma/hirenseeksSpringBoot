package com.hirenseeks.hirenseeks.user;

import java.util.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> userSignUp(User newUser) {
        Map<String, Object> response = new HashMap<>();

        System.out.println(newUser.toString());

        if (userRepository.existsByUserName(newUser.getUserName())) {
            response.put("success", false);
            response.put("error", "User name Taken");
            return response;
        }

        if (userRepository.existsByEmail(newUser.getEmail())) {
            response.put("success", false);
            response.put("error", "Email already Exist");
            return response;
        }

        if (userRepository.existsByContactNumber(newUser.getContactNumber())) {
            response.put("success", false);
            response.put("error", "Mobile already Exist");
            return response;
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savingUser = new User(newUser.getUserName(), newUser.getPassword(), newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(), newUser.getContactNumber());
        System.out.println(newUser);
        System.out.println(savingUser);
        userRepository.save(savingUser);

        response.put("User Name", savingUser.getUserName());
        response.put("First Name", savingUser.getFirstName());
        response.put("Last Name", savingUser.getLastName());
        response.put("E-Mail", savingUser.getEmail());
        response.put("Contact", savingUser.getContactNumber());
        response.put("success", true);

        return response;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> getUsers(String userName) {
        User u = userRepository.findUserByUserName(userName);

        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("id",u.getId());
        userInfo.put("userName",u.getUserName());
        userInfo.put("firstName",u.getFirstName());
        userInfo.put("lastName",u.getLastName());
        userInfo.put("email",u.getEmail());
        userInfo.put("dob",u.getDob());
        userInfo.put("contactNumber",u.getContactNumber());
        userInfo.put("address",u.getAddress());
        userInfo.put("city",u.getCity());
        userInfo.put("state",u.getState());
        userInfo.put("country",u.getCountry());
        userInfo.put("bio",u.getBio());
        userInfo.put("skills",u.getSkills());
        userInfo.put("projects",u.getProjects());
        userInfo.put("linkGithub",u.getLinkGithub());
        userInfo.put("linkLinkedIn",u.getLinkLinkedIn());
        userInfo.put("linkExtra",u.getLinkExtra());
        userInfo.put("is_recruiter",u.getIsRecruiter());

        return userInfo;
    }

    
    public Map<String, Object> updateUser(User user) {

        Map<String, Object> response = new HashMap<>();

        System.out.println(user.toString());

        User oldData = userRepository.findUserByUserName(user.getUserName());

        if (userRepository.existsByUserName(user.getUserName())) {
            response.put("success", false);
            response.put("error", "Username Taken");
            return response;
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            response.put("success", false);
            response.put("error", "Email Taken");
            return response;
        }

        if (userRepository.existsByContactNumber(user.getContactNumber())) {
            response.put("success", false);
            response.put("error", "Contact Taken");
            return response;
        }

        // User

        return null;
    }

    public String login(String username, String password) {

        boolean exists = userRepository.existsByUserName(username);

        if (exists) {
            return "Login Success";
        }

        return "Login Failed";
    }


}
