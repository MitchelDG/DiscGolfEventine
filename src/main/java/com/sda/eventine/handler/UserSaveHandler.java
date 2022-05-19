package com.sda.eventine.handler;

import com.sda.eventine.dto.UserDto;
import com.sda.eventine.dto.user.UserSaveRequest;
import com.sda.eventine.exception.UserSaveException;
import com.sda.eventine.model.User;
import com.sda.eventine.model.UserAccount;
import com.sda.eventine.model.UserInformation;
import com.sda.eventine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSaveHandler {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final String CLASS_NAME = UserSaveHandler.class.getName();

    @Transactional(rollbackFor = Exception.class)
    public UserDto createEdit(UserSaveRequest request) {

        User user = request.getId() == null ? new User() : userRepository.findById(request.getId()).orElseThrow(() ->
        {
            log.error("{} : createEdit : User with id - {} not found", CLASS_NAME, request.getId());
            throw new UserSaveException("User with given id not found");
        });

        UserInformation information = request.getId() == null ? new UserInformation() : user.getInformation();

        UserAccount account = request.getId() == null ? new UserAccount() : user.getAccount();

        if (request.getId() == null) {
            user.setId(UUID.randomUUID());
            user.setCreatedBy(request.getUsername());
            user.setCreatedAt(LocalDateTime.now());
            information.setCreatedBy(request.getUsername());
            information.setCreatedAt(LocalDateTime.now());
            account.setCreatedBy(request.getUsername());
            account.setCreatedAt(LocalDateTime.now());
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        if (request.getIsLocked() != null) {
            user.setIsLocked(request.getIsLocked());
        }
        if (request.getIsEnabled() != null) {
            user.setIsEnabled(request.getIsEnabled());
        }
        if (request.getDegreeBefore() != null) {
            information.setDegreeBefore(request.getDegreeBefore());
        }
        if (request.getFirstname() != null) {
            information.setFirstname(request.getFirstname());
        }
        if (request.getLastname() != null) {
            information.setLastname(request.getLastname());
        }
        if (request.getDegreeAfter() != null) {
            information.setDegreeAfter(request.getDegreeAfter());
        }
        if (request.getEmail() != null) {
            if (userRepository.existsByInformation_Email(request.getEmail())) {
                log.error("{} : createEdit : user with email - {} already registered", CLASS_NAME, request.getEmail());
                throw new UserSaveException("Given email already taken");
            }
            information.setEmail(request.getEmail());
        }
        if (request.getBirthdate() != null) {
            if (!request.getBirthdate().isBefore(LocalDate.now())) {
                log.error("{} : createEdit : birthdate must be set to date in past", CLASS_NAME);
                throw new UserSaveException("Birthdate must be set to date in past");
            }
            information.setBirthdate(request.getBirthdate());
        }
        if (request.getUsername() != null) {
            account.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            account.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            //TODO request password change confirmation on existing account
        }
        if (request.getIsActive() != null) {
            account.setIsActive(request.getIsActive());
        }
        user.setInformation(information);
        user.setAccount(account);
        information.setUser(user);
        account.setUser(user);

        userRepository.save(user);

        return UserDto.builder()
                .id(user.getId())
                .username(account.getUsername())
                .degreeBefore(information.getDegreeBefore())
                .firstname(information.getFirstname())
                .lastname(information.getLastname())
                .degreeAfter(information.getDegreeAfter())
                .birthdate(information.getBirthdate())
                .email(information.getEmail())
                .role(user.getRole())
                .locked(user.getIsLocked())
                .active(account.getIsActive())
                .enabled(user.getIsEnabled())
                .build();
    }
}
