package com.sda.eventine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity(name = "event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    Long id;

    @JsonProperty(value = "event_name")
    String name;
    //TODO set max char restriction to 500 characters
    @JsonProperty(value = "description")
    String description;

    @OneToOne
    @JsonProperty(value = "owner")
    User owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "participants")
    List<User> participantsId;


}
