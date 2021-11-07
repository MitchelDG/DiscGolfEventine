package com.sda.eventine.dto.appuser.registration.email;

public interface EmailSender {
    void send(String to, String email);
}
