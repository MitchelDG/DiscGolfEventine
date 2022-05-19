package com.sda.eventine.model;


import com.sda.eventine.dto.UserRole;
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserInformation information;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserAccount account;

    private UserRole role;

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
