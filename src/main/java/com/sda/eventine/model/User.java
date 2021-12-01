package com.sda.eventine.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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

    private String role;

    private String password;

    private boolean locked;

    private boolean enabled;

}
