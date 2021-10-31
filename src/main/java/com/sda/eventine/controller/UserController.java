package com.sda.eventine.controller;

import com.sda.eventine.exception.UserNotFoundException;
import com.sda.eventine.model.User;
import com.sda.eventine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

//TODO: replace body with DTO
    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        return ResponseEntity.ok(repository.save(user));
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        if (repository.existsById(id)) {
        return ResponseEntity.ok(repository.findById(id).get()); }

        throw new UserNotFoundException(String.format("User with id %d not found", id));
    }
}
