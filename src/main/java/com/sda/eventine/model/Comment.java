package com.sda.eventine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String body;

    @OneToOne
    @JoinColumn(referencedColumnName = "name")
    private User publisher;


    @JsonProperty(value = "event_id")
    private Long eventId;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;


    public Comment(String body, User publisher, Long eventId) {
        this.body = body;
        this.publisher = publisher;
        this.eventId = eventId;
    }

}
