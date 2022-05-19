package com.sda.eventine.dto.user;

import com.sda.eventine.dto.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequest {

    private UUID id;
    private String username;
    private String degreeBefore;
    private String firstname;
    private String lastname;
    private String degreeAfter;
    private String email;
    private String password;
    private LocalDate birthdate;
    private UserRole role;
    private Boolean isEnabled;
    private Boolean isLocked;
    private Boolean isActive;

}
