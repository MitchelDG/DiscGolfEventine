package com.sda.eventine.repository;

import com.sda.eventine.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
