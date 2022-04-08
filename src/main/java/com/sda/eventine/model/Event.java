package com.sda.eventine.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(fluent = false, chain = true)
public class Event {


    @Id
    @GeneratedValue
    private UUID id;

    @JsonProperty(value = "name")
    private String name;
    //TODO set max char restriction to 500 characters OR/AND let user know in text/input field
    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "capacity")
    private Long capacity;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    private LocalDateTime start;

    private LocalDateTime end;


}
