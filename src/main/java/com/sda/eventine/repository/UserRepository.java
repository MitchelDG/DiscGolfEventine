package com.sda.eventine.repository;

import com.sda.eventine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT u From User u where u.information.email= :email")
    User findByEmail(String email);

    boolean existsByInformation_Email(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isEnabled = TRUE WHERE u.information.email = :email")
    void enableUser(String email);

}
