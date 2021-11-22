package com.sda.eventine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "event")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(value = "name")
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

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "comment_list")
    private List<Comment> commentList;
}
