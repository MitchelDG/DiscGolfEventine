package com.sda.eventine.repository;

import com.sda.eventine.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
