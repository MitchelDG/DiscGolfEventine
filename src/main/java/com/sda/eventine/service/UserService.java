package com.sda.eventine.service;

import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.dto.UserRole;
import com.sda.eventine.exception.UserNotFoundException;
import com.sda.eventine.model.User;
import com.sda.eventine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final ParticipationService participationService;


    public void signUpUser(UserDTO user) {

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("this email is already registered");
        }

        User temp = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(UserRole.USER)
                .password(encoder.encode(user.getPassword()))
                .build();
        repository.save(temp);

    }

    public void enableUser(String email) {
        repository.enableUser(email);
    }


    public User getUserByEmail(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(
                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public User findById(Long id) {

        if (repository.findById(id).isEmpty()) {

            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MSG, id));
        } else return repository.getById(id);
    }

    public List<User> getAll() {

        return repository.findAll();
    }

    public void deleteUser(Long id) {

        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MSG, id));
        } else repository.deleteById(id);
    }

    public List<User> getParticipants(Long eventId) {
        List<User> userList = new LinkedList<>();
        var idList = participationService.getUsersForEvent(eventId);

        for (Long id : idList) {
            userList.add(findById(id));
        }

        return userList;

    }


}
