package com.sda.eventine.service;

import com.sda.eventine.dto.UserFacade;
import com.sda.eventine.exception.UserSaveException;
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
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private static final String USER_NOT_FOUND_MSG = "user with email {} not found";
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final ParticipationService participationService;




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


    public UserFacade findById(UUID id) {

        if (repository.findById(id).isEmpty()) {
            throw new UserSaveException(String.format("User with id %s not found", id));

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


    public void deleteUser(UUID id) {

        if (repository.findById(id).isEmpty()) {
            throw new UserSaveException(String.format(USER_NOT_FOUND_MSG, id));
        } else repository.deleteById(id);
    }


    public List<UserFacade> getParticipants(UUID eventId) {
        var userList = new LinkedList<UserFacade>();
        var idList = participationService.getUsersForEvent(eventId);

        for (UUID id : idList) {
            userList.add(findById(id));
        }
        return userList;
    }


    private UserFacade getUserFacade(User user) {
        return new UserFacade(user.getInformation().getEmail(), user.getInformation().getLastname());
    }


    public List<String> participantNames(UUID eventId) {
        var participants = getParticipants(eventId);
        List<String> names = new LinkedList<>();

        for (UserFacade userFacade : participants) {
            names.add(userFacade.getName());
        }

        return names;
    }
}
