package com.sda.eventine.dto.appuser.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo repository;

    public void saveConfirmationToken(ConfirmationToken token) {
        repository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return repository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        repository.updateConfirmedAt(token, LocalDateTime.now());
    }

}
