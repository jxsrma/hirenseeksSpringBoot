package com.hirenseeks.hirenseeks.job;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE j.id = ?1")
    Job findJobById(Long id);

    List<Job> findAllByPostedBy(String postedBy);
}
