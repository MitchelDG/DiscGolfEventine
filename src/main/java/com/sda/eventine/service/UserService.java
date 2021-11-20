package com.sda.eventine.service;

import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.dto.UserRole;
import com.sda.eventine.exception.UserNotFoundException;
import com.sda.eventine.model.User;
import com.sda.eventine.registration.token.ConfirmationTokenService;
import com.sda.eventine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    }

    public void enableUser(String email) {
        repository.enableUser(email);
    }


    public User getUserByEmail(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(
                String.format("User with email %s not found", email)));
    }

    public User findById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));
        } else return repository.getById(id);
    }

    public List<User> getAll() {

        return repository.findAll();
    }

    public void deleteUser(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));
        } else repository.deleteById(id);
    }

}
