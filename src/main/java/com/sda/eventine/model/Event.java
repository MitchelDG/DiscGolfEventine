package com.sda.eventine.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "event")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
public class Event {


    @Id
    @GeneratedValue
    private Long id;

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

//    @OneToOne
//    @JsonProperty(value = "owner")
//    @JoinColumn(referencedColumnName = "name")
//    private User owner;

}
