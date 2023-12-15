package com.hirenseeks.hirenseeks.job;

import lombok.Data;

@Data
public class JobRequest {
    private String title;
    private String jobPos;
    private String desc;
    private String timing;
    private String[] reqSkill;
    private String expLevel;
    private String location;

}
