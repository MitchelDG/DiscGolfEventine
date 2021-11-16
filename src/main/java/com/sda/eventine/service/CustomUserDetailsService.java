package com.sda.eventine.service;

import com.sda.eventine.dto.CustomUserDetails;
import com.sda.eventine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        } else throw new UsernameNotFoundException(String.format("User with email %s is not registered", username));



    }
}
