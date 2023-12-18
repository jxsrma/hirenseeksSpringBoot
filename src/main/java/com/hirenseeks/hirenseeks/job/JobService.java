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
    JobResponse jobResponse;

    public List<Map<String, Object>> getJobs() {
        List<Job> jobList = new ArrayList<>(jobRepository.findAll());
        List<Map<String, Object>> response = new ArrayList<>();

        for (Job job : jobList) {
            response.add(job.getJob());
        }
        return response;
    }

    public Map<String, Object> getJob(Long id) {
        try {
            Job job = jobRepository.findJobById(id);
            return jobResponse.returnSuccessTrueResponse("data", job.getJob());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobResponse.returnSuccessFalseResponse();
        }
    }

    public Map<String, Object> postJob(JobRequest jobRequest, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return jobResponse.returnUserNullResponse();
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (!user.getIs_recruiter()) {
                return jobResponse.returnNonRecruterResponse();
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

            return jobResponse.returnSuccessTrueResponse("data", jobSave);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobResponse.returnSuccessFalseResponse();
        }
    }

    public Map<String, Object> jobPostedBy(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return jobResponse.returnUserNullResponse();
            }
            String username = (String) (session.getAttribute("username"));
            List<Job> allJobs = new ArrayList<>(jobRepository.findAllByPostedBy(username));

            return jobResponse.returnSuccessTrueResponse("Job", allJobs);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobResponse.returnSuccessFalseResponse();

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
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return jobResponse.returnUserNullResponse();
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (user.getIs_recruiter()) {
                return jobResponse.returnNonUserResponse();
            }

            Job job = jobRepository.findJobById(id);
            List<String> applicants = new ArrayList<>(job.getAppliedPeople());

            if (applicants.contains(user.getId() + "")) {
                return jobResponse.returnUserAlreadyAppliedResponse();
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

            return jobResponse.returnSuccessTrueResponse("user applied", username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobResponse.returnSuccessFalseResponse();

        }
    }

    public Map<String, Object> cancelJob(Long id, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return jobResponse.returnUserNullResponse();
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (user.getIs_recruiter()) {
                return jobResponse.returnNonUserResponse();
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

                return jobResponse.returnSuccessTrueResponse();
            }
            return jobResponse.returnSuccessFalseResponse("User was Not Applied to the job");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobResponse.returnSuccessFalseResponse();
        }
    }

    public Map<String, Object> userAppliedJobs(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return jobResponse.returnUserNullResponse();
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (user.getIs_recruiter()) {
                return jobResponse.returnNonUserResponse();
            }

            List<String> appliedJobList = new ArrayList<>(user.getAppliedJobsTo());
            List<Map<String, Object>> jobList = new ArrayList<>();

            for (String string : appliedJobList) {
                if (!string.equals(" ")) {
                    Job jobData = jobRepository.findJobById(Long.parseLong(string));
                    jobList.add(jobData.getJob());
                }
            }
            return jobResponse.returnSuccessTrueResponse("Jobs", jobList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobResponse.returnSuccessFalseResponse();

        }
    }

    public Map<String, Object> deleteJob(Long id, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return jobResponse.returnUserNullResponse();
            }
            String username = (String) (session.getAttribute("username"));
            User user = userRepository.findUserByUserName(username);

            if (!user.getIs_recruiter() && user.getUserName() != username) {
                return jobResponse.returnSuccessFalseResponse("User Cannot Delete Jobs");
            }
            jobRepository.deleteById(id);

            return jobResponse.returnSuccessTrueResponse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobResponse.returnSuccessFalseResponse();

        }

    }

}
