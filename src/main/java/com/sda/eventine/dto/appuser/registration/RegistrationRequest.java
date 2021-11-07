package com.sda.eventine.dto.appuser.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    @JsonProperty(value = "first_name")
    private final String firstName;
    @JsonProperty(value = "last_name")
    private final String lastName;

    private final String email;

    private final String password;
}
