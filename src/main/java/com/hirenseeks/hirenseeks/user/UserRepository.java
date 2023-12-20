package com.hirenseeks.hirenseeks.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findUserById(Long id);

    /**
     * Find User by username.
     *
     * @param userName Username of the User.
     * @return User Object form database.
     */
    // @throws IllegalArgumentException If either operand is negative.
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findUserByUserName(String userName);

    /**
     * Check if the user exists by username.
     *
     * @param username Username of the User.
     * @return true if present else false.
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName = ?1")
    boolean existsByUserName(String username);

    /**
     * Find User by email.
     *
     * @param email Username of the User.
     * @return User Object form database.
     */
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    /**
     * Check if the user exists by email.
     *
     * @param email Username of the User.
     * @return true if present else false.
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);

    /**
     * Find User by contact contactNumber.
     *
     * @param contactNumber Username of the User.
     * @return User Object form database.
     */
    @Query("SELECT u FROM User u WHERE u.contactNumber = ?1")
    User findUserByContactNumber(String contactNumber);

    /**
     * Check if the user exists by contactNumber.
     *
     * @param contactNumber Username of the User.
     * @return true if present else false.
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.contactNumber = ?1")
    boolean existsByContactNumber(String contactNumber);

}
