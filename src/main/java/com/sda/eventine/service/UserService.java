package com.sda.eventine.service;

import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.dto.UserRole;
import com.sda.eventine.exception.UserNotFoundException;
import com.sda.eventine.model.User;
import com.sda.eventine.registration.token.ConfirmationToken;
import com.sda.eventine.registration.token.ConfirmationTokenService;
import com.sda.eventine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final ConfirmationTokenService tokenService;



    public void signUpUser(UserDTO user) {

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("this email is already registered");
        }

        User temp = new User();
        temp.setEmail(user.getEmail());
        temp.setName(user.getName());
        temp.setRole(UserRole.USER);
        temp.setPassword(encoder.encode(user.getPassword()));
        repository.save(temp);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .userEmail(user.getEmail())
                .build();

        tokenService.saveConfirmationToken(confirmationToken);

        //TODO: at this point we want to send email to the user to confirm registration
        //send confirmation token
    }

    public void enableAppUser(String email) {
        repository.enableUser(email);
    }


    public User getUserByEmail(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(
                String.format("User with email %s not found", email)
        ));
    }

    public User findById(Long id) {

        if (repository.findById(id).isEmpty()) {

            throw new UserNotFoundException(String.format("User with id %d not found", id));

        } else return repository.getById(id);

    }

    public List<User> getAll() {

        return repository.findAll();
    }


}
