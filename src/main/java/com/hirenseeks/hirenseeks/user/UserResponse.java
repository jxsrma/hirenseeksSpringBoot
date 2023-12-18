package com.hirenseeks.hirenseeks.user;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserResponse {

    public Map<String, Object> getUserData(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("userName", user.getUserName());
        data.put("firstName", user.getFirstName());
        data.put("lastName", user.getLastName());
        data.put("email", user.getEmail());
        data.put("dob", user.getDob());
        data.put("contactNumber", user.getContactNumber());
        data.put("address", user.getAddress());
        data.put("city", user.getCity());
        data.put("state", user.getState());
        data.put("country", user.getCountry());
        data.put("bio", user.getBio());
        data.put("skills", user.getSkills());
        data.put("projects", user.getProjects());
        data.put("linkGithub", user.getLinkGithub());
        data.put("linkLinkedIn", user.getLinkLinkedIn());
        data.put("linkExtra", user.getLinkExtra());
        data.put("is_recruiter", user.getIs_recruiter());
        return data;
    }

    public Map<String, Object> getUserInfo(User user) {
        Map<String, Object> userInfo = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = "";

        if (user.getDob() != null) {
            formattedDate = sdf.format(user.getDob());
        }

        userInfo.put("id", user.getId());
        userInfo.put("userName", user.getUserName());
        userInfo.put("firstName", user.getFirstName());
        userInfo.put("lastName", user.getLastName());
        userInfo.put("email", user.getEmail());
        userInfo.put("dob", formattedDate);
        userInfo.put("contactNumber", user.getContactNumber());
        userInfo.put("address", user.getAddress());
        userInfo.put("city", user.getCity());
        userInfo.put("state", user.getState());
        userInfo.put("country", user.getCountry());
        userInfo.put("bio", user.getBio());
        userInfo.put("skills", user.getSkills());
        userInfo.put("projects", user.getProjects());
        userInfo.put("linkGithub", user.getLinkGithub());
        userInfo.put("linkLinkedIn", user.getLinkLinkedIn());
        userInfo.put("linkExtra", user.getLinkExtra());
        userInfo.put("is_recruiter", user.getIs_recruiter());

        return userInfo;
    }

    public Map<String, Object> getUserLoginInfo(User user) {
        Map<String, Object> userLoginInfo = new HashMap<>();

        userLoginInfo.put("User Name", user.getUserName());
        userLoginInfo.put("First Name", user.getFirstName());
        userLoginInfo.put("Last Name", user.getLastName());
        userLoginInfo.put("E-Mail", user.getEmail());
        userLoginInfo.put("Contact", user.getContactNumber());
        userLoginInfo.put("success", true);
        return userLoginInfo;
    }

}
