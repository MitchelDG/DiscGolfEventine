package com.sda.eventine.repository;

import com.sda.eventine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
