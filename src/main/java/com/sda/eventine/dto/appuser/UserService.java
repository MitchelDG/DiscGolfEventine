package com.sda.eventine.dto.appuser;

import com.sda.eventine.dto.appuser.registration.token.ConfirmationToken;
import com.sda.eventine.dto.appuser.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserRepo repository;
    private final BCryptPasswordEncoder encoder;
    private final ConfirmationTokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser user) {
        boolean userExists = repository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("this email is already registered");
        }

        String encodedPassword = encoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        repository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .appUser(user)
                .build();

        tokenService.saveConfirmationToken(confirmationToken);

        //TODO: at this point we want to send email to the user to confirm registration

        return token;
    }

    public void enableAppUser(String email) {
        repository.enableAppUser(email);
    }

}
