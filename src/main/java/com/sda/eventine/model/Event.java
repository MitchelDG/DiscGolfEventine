package com.sda.eventine.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(schema = "event", name = "event")
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

    private LocalDateTime start;

    private LocalDateTime end;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;
}
