package com.hirenseeks.hirenseeks.job;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;

public interface JobService {

    public List<Map<String, Object>> getJobs();

    public Map<String, Object> getJob(Long id);

    public Map<String, Object> postJob(JobRequest jobRequest, HttpServletRequest request);

    public Map<String, Object> jobPostedBy(HttpServletRequest request);

    public List<Object> jobApplications(Long id);

    public Map<String, Object> apply(Long id, HttpServletRequest request);

    public Map<String, Object> cancelJob(Long id, HttpServletRequest request);

    public Map<String, Object> userAppliedJobs(HttpServletRequest request);

    public Map<String, Object> deleteJob(Long id, HttpServletRequest request);

}
