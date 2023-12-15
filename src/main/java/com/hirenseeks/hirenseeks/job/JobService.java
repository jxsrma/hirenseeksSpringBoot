package com.hirenseeks.hirenseeks.job;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenseeks.hirenseeks.user.User;
import com.hirenseeks.hirenseeks.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class JobService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;

    public List<Map<String, Object>> getJobs() {
        List<Job> jobList = new ArrayList<>(jobRepository.findAll());
        List<Map<String, Object>> response = new ArrayList<>();

        for (Job job : jobList) {
            response.add(job.getJob());
        }
        return response;
    }

    public Map<String, Object> getJob(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Job job = jobRepository.findJobById(id);
            response.put("success", true);
            response.put("data", job.getJob());
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> postJob(JobRequest jobRequest, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.put("success", false);
                return response;
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (!user.getIs_recruiter()) {
                response.put("message", "User Cannot Post Jobs");
                return response;
            }
            Job jobSave = new Job();
            jobSave.setPostedBy(username);
            jobSave.setExpLevel(jobRequest.getExpLevel());
            jobSave.setReqSkill(jobRequest.getReqSkill());
            jobSave.setDesc(jobRequest.getDesc());
            jobSave.setJobPos(jobRequest.getJobPos());
            jobSave.setTiming(jobRequest.getTiming());
            jobSave.setTitle(jobRequest.getTitle());
            jobSave.setLocation(jobRequest.getLocation());
            jobSave.setJobDate(new Date());


            jobRepository.save(jobSave);
            response.put("success", true);
            response.put("data", jobSave);

            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> jobPostedBy(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.put("success", false);
                return response;
            }
            String username = (String) (session.getAttribute("username"));

            List<Job> allJobs = new ArrayList<>(jobRepository.findAllByPostedBy(username));

            response.put("Job", allJobs);

            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public List<Object> jobApplications(Long id) {
        Job job = jobRepository.findJobById(id);
        List<String> appliedJobList = new ArrayList<>(
                job.getAppliedPeople() == null ? new ArrayList<>() : job.getAppliedPeople());
        List<Object> allUsers = new ArrayList<>();

        for (String string : appliedJobList) {
            if (!string.equals(" ")) {
                User user = userRepository.findUserById(Long.parseLong(string));
                allUsers.add(user.userData());
            }
        }
        return allUsers;
    }

    public Map<String, Object> apply(Long id, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.put("success", false);
                return response;
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (user.getIs_recruiter()) {
                response.put("message", "Recruiter cannot apply to jobs");
                return response;
            }

            Job job = jobRepository.findJobById(id);
            List<String> applicants = new ArrayList<>(job.getAppliedPeople());

            if (applicants.contains(user.getId() + "")) {
                response.put("success", false);
                response.put("error", "Already Applied");
                return response;
            }

            // Job
            applicants.add(user.getId() + "");
            job.setAppliedPeople(applicants);
            jobRepository.save(job);

            // User
            List<String> userAppliedJobs = new ArrayList<>(user.getAppliedJobsTo());
            userAppliedJobs.add(job.getId() + "");
            user.setAppliedJobsTo(userAppliedJobs);
            userRepository.save(user);

            response.put("success", true);
            response.put("user applied", username);
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> cancelJob(Long id, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.put("success", false);
                return response;
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (user.getIs_recruiter()) {
                response.put("message", "Recruiter cannot perfom this action");
                return response;
            }

            Job job = jobRepository.findJobById(id);
            List<String> applicants = new ArrayList<>(job.getAppliedPeople());

            if (applicants.contains(user.getId() + "")) {
                applicants.remove(user.getId() + "");
                job.setAppliedPeople(applicants);
                jobRepository.save(job);

                List<String> userAppliedJobs = new ArrayList<>(user.getAppliedJobsTo());
                userAppliedJobs.remove(job.getId() + "");
                user.setAppliedJobsTo(userAppliedJobs);
                userRepository.save(user);

                response.put("success", true);
                return response;
            }

            response.put("success", false);
            response.put("error", "User was Not Applied to the job");
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

    public Map<String, Object> userAppliedJobs(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.put("success", false);
                return response;
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (user.getIs_recruiter()) {
                response.put("message", "Recruiter cannot perfom this action");
                return response;
            }

            List<String> appliedJobList = new ArrayList<>(user.getAppliedJobsTo());
            List<Map<String, Object>> jobList = new ArrayList<>();

            for (String string : appliedJobList) {
                if (!string.equals(" ")) {
                    Job jobData = jobRepository.findJobById(Long.parseLong(string));
                    jobList.add(jobData.getJob());
                }
            }
            response.put("success", true);
            response.put("Jobs", jobList);
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", false);
            return response;
        }
    }

}
