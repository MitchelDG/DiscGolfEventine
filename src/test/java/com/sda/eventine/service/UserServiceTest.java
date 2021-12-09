package com.sda.eventine.service;

import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.model.User;
import com.sda.eventine.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    ;
    @Mock
    BCryptPasswordEncoder passwordEncoder;
    @Mock
    ParticipationService participationService;
    @Mock
    UserRepository repository;

    @BeforeEach
    void beforeEach() {
    repository.save(new User(null, "John Doe", "em@il", "USER", "passwd", false, false));
    }

    @Test
    void signUpUser(@Mock UserRepository repository) {
        //given
        var cut = new UserService(repository, passwordEncoder, participationService);
        var obj = new UserDTO("John Doe", "em@il", "passwd");
        var user = new User(null, "John Doe", "em@il", "USER", "passwd", false, false);
        //when
        cut.signUpUser(obj);

        //then
        Assertions.assertThat(user).isEqualTo(repository.findByEmail("em@il"));


//        var cut = new User(null, "John Doe", "em@il", "USER", "passwd", false, false);
//        repository.save(cut);
//        userService.signUpUser(new UserDTO("John Doe", "em@il", "passwd"));
//        Assertions.assertThat(cut).isEqualTo(repository.findByEmail("em@il"));

    }

    @Test
    void enableUser() {
        //given

        //when

        //then
    }

    @Test
    void getUserByEmail(@Mock UserRepository repository) {
        //given

        var cut = new UserService(repository, passwordEncoder, participationService);
        var user = new User(null, "em@il", "John Doe", "USER", "passwd", false, false);

        //when


        //then

        Assertions.assertThat(cut.getUserByEmail(user.getEmail())).isEqualTo(repository.findByEmail(user.getEmail()));
    }

    @Test
    void findById() {
        //given

        //when

        //then
    }

    @Test
    void getAll() {
        //given

        //when

        //then
    }

    @Test
    void deleteUser() {
        //given

        //when

        //then
    }

    @Test
    void getParticipants() {
        //given

        //when

        //then
    }
}