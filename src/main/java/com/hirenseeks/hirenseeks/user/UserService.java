package com.hirenseeks.hirenseeks.user;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Map<String, Object> userSignUp(User newUser) {
        Map<String, Object> response = new HashMap<>();

        try {

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

            newUser.setPassword(Encrypt.encryptPassword(newUser.getPassword()));
            User savingUser = new User(newUser.getUserName(), newUser.getPassword(), newUser.getFirstName(),
                    newUser.getLastName(),
                    newUser.getEmail(), newUser.getContactNumber());
            userRepository.save(savingUser);

            response.put("User Name", savingUser.getUserName());
            response.put("First Name", savingUser.getFirstName());
            response.put("Last Name", savingUser.getLastName());
            response.put("E-Mail", savingUser.getEmail());
            response.put("Contact", savingUser.getContactNumber());
            response.put("success", true);

            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> currUser(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> userData(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");
            User user = userRepository.findUserByUserName(username);
            return user.userData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> getUsers(String userName) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> userInfo = new HashMap<>();
            User u = userRepository.findUserByUserName(userName);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = "";
            if (u.getDob() != null) {
                formattedDate = sdf.format(u.getDob());
            }

            userInfo.put("id", u.getId());
            userInfo.put("userName", u.getUserName());
            userInfo.put("firstName", u.getFirstName());
            userInfo.put("lastName", u.getLastName());
            userInfo.put("email", u.getEmail());
            userInfo.put("dob", formattedDate);
            userInfo.put("contactNumber", u.getContactNumber());
            userInfo.put("address", u.getAddress());
            userInfo.put("city", u.getCity());
            userInfo.put("state", u.getState());
            userInfo.put("country", u.getCountry());
            userInfo.put("bio", u.getBio());
            userInfo.put("skills", u.getSkills());
            userInfo.put("projects", u.getProjects());
            userInfo.put("linkGithub", u.getLinkGithub());
            userInfo.put("linkLinkedIn", u.getLinkLinkedIn());
            userInfo.put("linkExtra", u.getLinkExtra());
            userInfo.put("is_recruiter", u.getIs_recruiter());

            return userInfo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> updateUser(User user, HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();
        try {


            HttpSession session = request.getSession(false);
            if (session == null) {
                response.put("success", false);
                return response;
            }
            String username = (String) (session.getAttribute("username"));
            User oldUserData = userRepository.findUserByUserName(username);

            if (!username.equals(user.getUserName()) && userRepository.existsByUserName(user.getUserName())) {
                response.put("success", false);
                response.put("error", "Username Taken");
                return response;
            } else {
                oldUserData.setUserName(user.getUserName());
                session.invalidate();
            }
            if (!oldUserData.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
                response.put("success", false);
                response.put("error", "Email Taken");
                return response;
            } else {
                oldUserData.setEmail(user.getEmail());
            }

            if (!oldUserData.getContactNumber().equals(user.getContactNumber())
                    && userRepository.existsByContactNumber(user.getContactNumber())) {
                response.put("success", false);
                response.put("error", "Contact Taken");
                return response;
            } else {
                oldUserData.setContactNumber(user.getContactNumber());
            }
            oldUserData.setFirstName(user.getFirstName());
            oldUserData.setLastName(user.getLastName());
            oldUserData.setBio(user.getBio());
            oldUserData.setDob(user.getDob());
            oldUserData.setAddress(user.getAddress());
            oldUserData.setCity(user.getCity());
            oldUserData.setState(user.getState());
            oldUserData.setCountry(user.getCountry());
            oldUserData.setSkills(user.getSkillsList());
            oldUserData.setProjects(user.getProjectsList());
            oldUserData.setLinkGithub(user.getLinkGithub());
            oldUserData.setLinkLinkedIn(user.getLinkLinkedIn());
            oldUserData.setLinkExtra(user.getLinkExtra());

            userRepository.save(oldUserData);

            response.put("success", true);
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.put("success", true);
            response.put("User", "None: Logged Out");
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

}
