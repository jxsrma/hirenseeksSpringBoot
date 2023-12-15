package com.hirenseeks.hirenseeks.job;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "jobDate")
    private Date jobDate;

    @Column(name = "title")
    private String title;

    @Column(name = "jobPos")
    private String jobPos;

    @Column(name = "description")
    private String desc;

    @Column(name = "timing")
    private String timing;

    @Column(name = "reqSkill")
    private String reqSkill;

    @Column(name = "expLevel")
    private String expLevel;

    @Column(name = "postedBy")
    private String postedBy;

    @Column(name = "location")
    private String location;

    @Column(name = "appliedPeople")
    private String appliedPeople;

    public void setReqSkill(String s) {
        this.reqSkill = s;
    }

    public void setReqSkill(String[] reqSkillList) {
        this.reqSkill = String.join("','", reqSkillList);
        this.reqSkill = "['" + this.reqSkill + "']";
    }

    public void setReqSkill(List<String> reqSkillList) {
        this.reqSkill = String.join("','", reqSkillList);
        this.reqSkill = "['" + this.reqSkill + "']";
    }

    public void setAppliedPeople(List<String> appliedPeopleList) {
        this.appliedPeople = String.join(" ", appliedPeopleList);
    }

    public List<String> getAppliedPeople() {
        return appliedPeople != null ? Arrays.asList(this.appliedPeople.split(" ")) : new ArrayList<>();

    }

    public Map<String, Object> getJob() {
        Map<String, Object> jobMap = new HashMap<>();
        jobMap.put("id", this.id);
        jobMap.put("title", this.title);
        jobMap.put("jobPos", this.jobPos);
        jobMap.put("desc", this.desc);
        jobMap.put("reqSkill", this.reqSkill);
        jobMap.put("expLevel", this.expLevel);
        jobMap.put("location", this.location);
        jobMap.put("timing", this.timing);
        jobMap.put("postedBy", this.postedBy);
        jobMap.put("jobDate", this.jobDate);
        jobMap.put("appliedPeople", this.appliedPeople);

        return jobMap;

    }
}
