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
public class Participation {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "event_id")
    private Long eventId;

}
