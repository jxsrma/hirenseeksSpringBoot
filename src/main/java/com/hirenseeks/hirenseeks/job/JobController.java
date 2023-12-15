package com.hirenseeks.hirenseeks.job;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;

@RestController
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping(path = "/jobs")
    public List<Map<String, Object>> getJobs() {
        return jobService.getJobs();
    }

    @GetMapping("/job-posted")
    public Map<String, Object> postMethodName(HttpServletRequest request) {
        return jobService.jobPostedBy(request);
    }

    @GetMapping(path = "/job/{id}")
    public Map<String, Object> job(@PathVariable("id") Long id) {
        return jobService.getJob(id);
    }

    @PostMapping(path = "/post-for-recruitment")
    public Map<String, Object> postJob(@RequestBody JobRequest jobRequest, HttpServletRequest request) {
        return jobService.postJob(jobRequest, request);
    }

    @GetMapping("applications/{id}")
    public List<Object> jobApplications(@PathVariable("id") Long id) {
        return jobService.jobApplications(id);
    }

    @GetMapping(path = "/apply/{id}")
    public Map<String, Object> getUser(@PathVariable("id") Long id, HttpServletRequest request) {
        return jobService.apply(id, request);

    }

    @GetMapping(path = "/cancelJob/{id}")
    public Map<String, Object> cancelJob(@PathVariable("id") Long id, HttpServletRequest request) {
        return jobService.cancelJob(id, request);

    }

    @GetMapping(path = "/job-applied")
    public Map<String, Object> userAppliedJobs(HttpServletRequest request) {
        return jobService.userAppliedJobs(request);

    }

}
