package com.sda.eventine.model;

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
@Table(schema = "comments", name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    private UUID id;

    private String body;

    @JoinColumn(name = "user_id")
    private UUID userId;

    @JoinColumn(name = "event_id")
    private UUID eventId;

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

    public Comment(String body, UUID userId, UUID eventId) {
        this.body = body;
        this.userId = userId;
        this.eventId = eventId;
    }
}
