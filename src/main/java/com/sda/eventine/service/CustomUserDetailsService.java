package com.sda.eventine.service;

import com.sda.eventine.dto.CustomUserDetails;
import com.sda.eventine.exception.NotAuthorizedException;
import com.sda.eventine.model.User;
import com.sda.eventine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.extras.springsecurity5.auth.Authorization;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username);

        if (user == null) {

            log.error("User with email {} is not registered", username);
            throw new UsernameNotFoundException("Username not found in database");

        } else return new CustomUserDetails(user);
    }


    public User getCurrentUser() {
        var user = new User();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.getPrincipal();

        } catch (NotAuthorizedException e) {
            log.error("Current user is anonymous");
        }
        return user;
    }

    public User getPrincipal() {
        return null;
    }

}

