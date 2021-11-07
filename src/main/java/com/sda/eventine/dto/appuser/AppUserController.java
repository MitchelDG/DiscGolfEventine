package com.sda.eventine.dto.appuser;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/user")
public class AppUserController {

    private final UserService userService;
    private final UserRepo userRepository;

    public AppUserController(UserService userService, UserRepo userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/{id}")
    public AppUser getUserById(@PathVariable Long id) {

        return userRepository.getById(id);
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers() {

        return userRepository.findAll();
    }

}
