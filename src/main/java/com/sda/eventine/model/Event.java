package com.sda.eventine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(value = "event_name")
    private String name;
    //TODO set max char restriction to 500 characters
    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    private LocalDateTime start;

    private LocalDateTime end;

    @OneToOne
    @JsonProperty(value = "owner")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "participants")
    private List<User> participantsId;


}
