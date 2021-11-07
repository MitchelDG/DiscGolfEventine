package com.sda.eventine.repository;

import com.sda.eventine.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    public boolean existsByName(Event event);

    public Event getEventByName(String name);
}
