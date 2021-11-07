package com.sda.eventine.service;

import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.exception.EventAlreadyExistsException;
import com.sda.eventine.model.Event;
import com.sda.eventine.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    //create

    public void createEvent(EventDTO event) {

        if(event.getName().equals(repository.getEventByName(event.getName()))) {
            throw new EventAlreadyExistsException(String
                    .format("Event with name %s already exists", event.getName()));
        }
        repository.save(Event.builder()
                .name(event.getName())
                .description(event.getDescription())
                .createdAt(event.getCreatedAt())
                .start(event.getStart())
                .end(event.getEnd())
                .owner(event.getOwner())
                .participantsId(event.getParticipants())
                .build()
        );

    }

    //find by id

    //find all

    //find by date

    //update

    //delete
}
