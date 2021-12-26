package com.sda.eventine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class CommentDTO {

    public CommentDTO(String body) {
        this.body = body;
    }

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(value = "body")
    private String body;

    @JsonProperty(value = "publisher_id")
    private Long publisherId;

}
