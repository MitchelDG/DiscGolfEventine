package com.sda.eventine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String body;

    @OneToOne
    private User publisher;

    @OneToOne
    @JsonProperty(value = "event_id")
    @JoinColumn(referencedColumnName = "id")
    private Event eventId;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;


    public Comment(String body, User publisher, Event eventId) {
        this.body = body;
        this.publisher = publisher;
        this.eventId = eventId;
    }

}
