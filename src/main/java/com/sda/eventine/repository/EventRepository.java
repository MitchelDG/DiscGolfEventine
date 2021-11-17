package com.sda.eventine.repository;

import com.sda.eventine.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByName(String name);

    Event getEventByName(String name);

    List<Event> getEventsByStartBetween(LocalDateTime from, LocalDateTime till);
}
