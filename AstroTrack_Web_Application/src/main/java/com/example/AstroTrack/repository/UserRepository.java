package com.example.AstroTrack.repository;

import com.example.AstroTrack.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the found user entity
     */
    @Query(value = "select * from users where username like %?1%", nativeQuery = true)
    Users findUserByUsername(String username);
}
