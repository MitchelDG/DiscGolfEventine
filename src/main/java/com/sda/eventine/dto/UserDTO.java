package com.sda.eventine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class UserDTO {

    private String name;

    @Email
    private String email;

    private String password;

    @JsonProperty(value = "user_role")
    private UserRole userRole;

    private Boolean locked = false; //ban state

    private Boolean enabled = false; //user - registration state, set true after confirming registration via email


    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
