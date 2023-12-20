package com.hirenseeks.hirenseeks.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenseeks.hirenseeks.response.CustomResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    CustomResponse customResponse = new CustomResponse();
    UserResponse userResponse = new UserResponse();

    public Map<String, Object> userSignUp(UserLogin userLogin) {

        try {

            if (userRepository.existsByUserName(userLogin.getUserName())) {
                return customResponse.returnSuccessFalseResponse("User name Taken");
            }

            if (userRepository.existsByEmail(userLogin.getEmail())) {
                return customResponse.returnSuccessFalseResponse("Email already Exist");
            }

            if (userRepository.existsByContactNumber(userLogin.getContactNumber())) {
                return customResponse.returnSuccessFalseResponse("Mobile already Exist");
            }

            userLogin.setPassword(Encrypt.encryptPassword(userLogin.getPassword()));
            User savingUser = new User(userLogin.getUserName(), userLogin.getPassword(), userLogin.getFirstName(),
                    userLogin.getLastName(),
                    userLogin.getEmail(), userLogin.getContactNumber(), new Date());
            userRepository.save(savingUser);
            return userResponse.getUserLoginInfo(savingUser);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return customResponse.returnSuccessFalseResponse();
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> currUser(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String username = (String) session.getAttribute("username");
                if (username != null) {
                    return customResponse.returnSuccessTrueResponse("User", username);
                }
            }
            return customResponse.returnUserNullResponse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return customResponse.returnSuccessFalseResponse();
        }
    }

    public Map<String, Object> userData(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");
            User user = userRepository.findUserByUserName(username);
            return userResponse.getUserData(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return customResponse.returnSuccessFalseResponse();
        }
    }

    public Map<String, Object> getUsers(String userName) {
        try {
            User u = userRepository.findUserByUserName(userName);
            return userResponse.getUserInfo(u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return customResponse.returnSuccessFalseResponse();
        }
    }

    public Map<String, Object> updateUser(User user, HttpServletRequest request) {

        try {

            HttpSession session = request.getSession(false);
            if (session == null) {
                return customResponse.returnUserNullResponse();
            }
            String username = (String) (session.getAttribute("username"));
            User oldUserData = userRepository.findUserByUserName(username);

            if (!username.equals(user.getUserName()) && userRepository.existsByUserName(user.getUserName())) {
                return customResponse.returnSuccessFalseResponse("User name Taken");
            } else {
                oldUserData.setUserName(user.getUserName());
                session.invalidate();
            }
            if (!oldUserData.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
                return customResponse.returnSuccessFalseResponse("Email already Exist");

            } else {
                oldUserData.setEmail(user.getEmail());
            }

            if (!oldUserData.getContactNumber().equals(user.getContactNumber())
                    && userRepository.existsByContactNumber(user.getContactNumber())) {
                return customResponse.returnSuccessFalseResponse("Mobile already Exist");

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

            return customResponse.returnSuccessTrueResponse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return customResponse.returnSuccessFalseResponse();
        }
    }

    public Map<String, Object> logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            return customResponse.returnSuccessTrueResponse("User", null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return customResponse.returnSuccessFalseResponse();
        }
    }

}
