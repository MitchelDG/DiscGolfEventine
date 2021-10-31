package com.sda.eventine.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email
    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "user_name")
    String name;

    @JsonProperty(value = "user_role")
    String role;

    @JsonProperty(value = "user_password")
    String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "participating_events")
    List<Event> participatingEvents;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "owned_events")
    List<Event> ownedEvents;
}
