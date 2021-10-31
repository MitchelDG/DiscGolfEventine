package com.sda.eventine.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    Integer id;

    @Email
    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "user_name")
    String name;

    @JsonProperty(value = "user_role")
    String role;

    @JsonProperty(value = "user_password")
    String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty(value = "participating_events_id")
    List<Integer> participatingEvents;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty(value = "owned_events_id")
    List<Integer> ownedEvents;
}
