package com.hirenseeks.hirenseeks.user;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findUserByUserName(String name);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName = ?1")
    boolean existsByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.contactNumber = ?1")
    User findUserByContactNumber(String contactNumber);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.contactNumber = ?1")
    boolean existsByContactNumber(String contactNumber);

}
