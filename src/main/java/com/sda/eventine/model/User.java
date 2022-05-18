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
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "users", name = "user")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private UserInformation information;

    @OneToOne
    private UserAccount account;

    private String role;

    @Column(name = "locked")
    private Boolean isLocked;

    @Column(name = "enabled")
    private Boolean isEnabled;

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

    @OneToMany
    private List<Participation> participations;
}
