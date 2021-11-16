package com.sda.eventine.registration.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Log
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;


    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("DGEventine registration confirmation");
            helper.setFrom("borek.michla@gmail.com");
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            log.warning("failed to send email");
            throw new IllegalStateException("failed to send email");
        }
    }
}
