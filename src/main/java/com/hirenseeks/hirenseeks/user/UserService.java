package com.hirenseeks.hirenseeks.user;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;

public interface UserService {
    public Map<String, Object> userSignUp(UserLogin userLogin);

    public Map<String, Object> currUser(HttpServletRequest request);

    public List<User> getUsers();

    public Map<String, Object> userData(HttpServletRequest request);

    public Map<String, Object> getUsers(String userName);

    public Map<String, Object> updateUser(User user, HttpServletRequest request);

    public Map<String, Object> logout(HttpServletRequest request);
}
