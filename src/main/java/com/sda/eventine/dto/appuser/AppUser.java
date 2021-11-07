package com.sda.eventine.dto.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
public class AppUser implements UserDetails {

    private static final String SEQUENCE_STRING = "app_user_sequence";

    @SequenceGenerator(name = SEQUENCE_STRING , sequenceName = SEQUENCE_STRING, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_STRING)
    private Long id;
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "user_role")
    private UserRole userRole;

    private Boolean locked = false; //ban state

    private Boolean enabled = false; //user - registration state, set true after confirming registration via email


    public AppUser(String firstName, String lastName, String email, String password, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
