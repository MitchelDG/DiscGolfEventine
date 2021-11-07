package com.sda.eventine.dto.appuser.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
