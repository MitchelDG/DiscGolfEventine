package com.sda.eventine.repository;

import com.sda.eventine.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

    @Query(value = "SELECT p.eventId FROM Participation p WHERE p.userId=?1")
    List<UUID> getEventsByUserId(UUID userId);

    @Query(value = "SELECT p.userId FROM Participation p WHERE p.eventId=?1")
    List<UUID> getUsersByEventId(UUID eventId);

    boolean existsByEventIdAndUserId(UUID eventId, UUID userId);
}
