
package com.sda.eventine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String body;

    @JoinColumn(table = "user", referencedColumnName = "id")
    @JoinColumn(referencedColumnName = "user_id")
    private Long publisherId;

    @JoinColumn(table = "event", referencedColumnName = "id")
    @JsonProperty(value = "event_id")
    private Long eventId;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;


    public Comment(String body, Long publisherId, Long eventId) {
        this.body = body;
        this.publisherId = publisherId;
        this.eventId = eventId;
    }
}

