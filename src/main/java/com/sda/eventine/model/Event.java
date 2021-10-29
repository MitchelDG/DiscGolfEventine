//package com.sda.eventine.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Set;
//
//@Entity(name = "event")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Event {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Column(name = "event_name")
//    private String eventName;
//
//    private String description;
//
//    @Column(name = "owner_id")
//    private Long ownerId;
//
//    @Column(name = "participants_id")
//    private Long participantsId;
//
//    @Column(name = "created_at")
//    @CreationTimestamp
//    private Timestamp createAt;
//
//
//
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Event parent;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Event> children;
//}
