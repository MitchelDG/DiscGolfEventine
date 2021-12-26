package com.sda.eventine.controller;

import com.sda.eventine.dto.UserFacade;
import com.sda.eventine.model.User;
import com.sda.eventine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

private final UserService service;


    @GetMapping(value = "/{id}")
    public UserFacade getUserById(@PathVariable Long id) {
        return service.findById(id);
    }


    @GetMapping(value = "/all")
    public List<UserFacade> getAllUsers() {
        return service.getAll();
    }


    @GetMapping(value = "/email/")
    public User getUserByEmail(@RequestParam("email") String email) {
       return service.getUserByEmail(email);
    }


    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable Long id) {
        service.deleteUser(id);
    }

}
