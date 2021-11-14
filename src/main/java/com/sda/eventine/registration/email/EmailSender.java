package com.sda.eventine.registration.email;

public interface EmailSender {
    void send(String to, String email);
}
