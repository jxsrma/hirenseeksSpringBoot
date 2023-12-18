package com.hirenseeks.hirenseeks.response;

import java.util.*;

public class CustomResponse {
    public Map<String, Object> returnSuccessTrueResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

    public Map<String, Object> returnSuccessTrueResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("error", message);
        return response;
    }

    public Map<String, Object> returnSuccessTrueResponse(String key, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put(key, data);
        return response;
    }

    public Map<String, Object> returnSuccessFalseResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        return response;
    }

    public Map<String, Object> returnSuccessFalseResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", message);
        return response;
    }

    public Map<String, Object> returnUserNullResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("User", null);
        return response;
    }

    public Map<String, Object> returnNonRecruterResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", "User cannot perform this action");
        return response;
    }

    public Map<String, Object> returnNonUserResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", "Recruiter cannot perform this action");
        return response;
    }

}
