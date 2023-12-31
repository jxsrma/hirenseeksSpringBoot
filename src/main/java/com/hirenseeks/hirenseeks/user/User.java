package com.hirenseeks.hirenseeks.user;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "isSuperuser")
    private Boolean isSuperuser = false;

    @Column(name = "userName")
    private String userName;

    @Column(name = "compName")
    private String compName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "bio")
    private String bio = "";

    @Column(name = "signUpDate")
    private Date signUpDate;

    @JsonIgnore
    @Column(name = "isStaff")
    private Boolean is_staff = false;

    @JsonIgnore
    @Column(name = "isActive")
    private Boolean is_active = true;

    @Column(name = "isRecruiter")
    private Boolean is_recruiter = false;

    @Column(name = "countryCode")
    private String countryCode;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "address")
    private String address = "";

    @Column(name = "city")
    private String city = "";

    @Column(name = "state")
    private String state = "";

    @Column(name = "country")
    private String country = "";

    @Column(name = "skills")
    private String skills = "";

    @Column(name = "projects")
    private String projects = "";

    @Column(name = "linkGithub")
    private String linkGithub = "";

    @Column(name = "linkLinkedIn")
    private String linkLinkedIn = "";

    @Column(name = "linkExtra")
    private String linkExtra = "";

    @Column(name = "appliedJobsTo")
    private String appliedJobsTo = "";

    public User() {
    }

    public User(String username, String password, String fName, String lName, String eMail, String contact, Date signUpDate) {
        this.userName = username;
        this.password = password;
        this.firstName = fName;
        this.lastName = lName;
        this.email = eMail;
        this.contactNumber = contact;
        this.signUpDate = signUpDate;
    }

    public void setAppliedJobsTo(List<String> appliedJobsToList) {
        this.appliedJobsTo = String.join(",", appliedJobsToList);
    }

    public List<String> getAppliedJobsTo() {
        return Arrays.asList(this.appliedJobsTo.split(","));
    }

    public void setSkills(List<String> skillList) {
        this.skills = String.join("','", skillList);
        this.skills = "['" + this.skills + "']";
    }

    public List<String> getSkillsList() {
        String s = this.skills;
        s = s.substring(2, s.length() - 2);
        return Arrays.asList(s.split("','"));
    }

    public void setProjects(List<String> skillList) {
        this.projects = String.join("','", skillList);
        this.projects = "['" + this.projects + "']";
    }

    public List<String> getProjectsList() {
        String p = this.projects;
        p = p.substring(2, p.length() - 2);
        return Arrays.asList(p.split("','"));
    }

}
