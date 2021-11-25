package com.sda.eventine.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sda.eventine.dto.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "name")
    private String name;


    @JsonProperty(value = "role")
    UserRole role;

    @JsonProperty(value = "password")
    String password;

    private boolean locked;

    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "participating_events")
    @JoinColumn(referencedColumnName = "id")
    private List<Event> participatingEvents;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "owned_events")
    @JoinColumn(referencedColumnName = "id")
    private List<Event> ownedEvents;



}
