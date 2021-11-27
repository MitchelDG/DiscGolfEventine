package com.sda.eventine.repository;

import com.sda.eventine.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Query(value = "SELECT p.eventId FROM Participation p WHERE p.userId=?1")
    List<Long> getEventsByUserId(Long userId);

    @Query(value = "SELECT p.userId FROM Participation p WHERE p.eventId=?1")
    List<Long> getUsersByEventId(Long eventId);
}
