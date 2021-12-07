package com.sda.eventine.service;

import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.dto.UserFacade;
import com.sda.eventine.exception.UserNotFoundException;
import com.sda.eventine.model.User;
import com.sda.eventine.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private static final String USER_NOT_FOUND_MSG = "user with email {} not found";
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final ParticipationService participationService;


    public void signUpUser(UserDTO user) {

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new IllegalStateException("this email is already registered");
        }

        User temp = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role("USER")
                .password(encoder.encode(user.getPassword()))
                .build();
        repository.save(temp);
    }


    public void enableUser(String email) {
        repository.enableUser(email);
    }


    public User getUserByEmail(String email) throws UsernameNotFoundException {
        var user = repository.findByEmail(email);

        if (user == null) {
            log.error(USER_NOT_FOUND_MSG, email);
            throw new UsernameNotFoundException("Username not found in database");

        } else return repository.findByEmail(email);
    }


    public UserFacade findById(Long id) {

        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));

        } else return getUserFacade(repository.getById(id));
    }


    public List<UserFacade> getAll() {
        var userList = repository.findAll();
        var userFacadeList = new ArrayList<UserFacade>();

        for (User user : userList) {
           userFacadeList.add(getUserFacade(user));
        }
        return userFacadeList;
    }


    public void deleteUser(Long id) {

        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MSG, id));
        } else repository.deleteById(id);
    }


    public List<UserFacade> getParticipants(Long eventId) {
        List<UserFacade> userList = new LinkedList<>();
        var idList = participationService.getUsersForEvent(eventId);

        for (Long id : idList) {
            userList.add(findById(id));
        }

        return userList;
    }


    private UserFacade getUserFacade(User user) {
        return new UserFacade(user.getEmail(), user.getName());
    }


    public List<String> participantNames(Long eventId) {
        var participants = getParticipants(eventId);
        List<String> names = new LinkedList<>();

        for (UserFacade userFacade : participants) {
            names.add(userFacade.getName());
        }

        return names;
    }
}
