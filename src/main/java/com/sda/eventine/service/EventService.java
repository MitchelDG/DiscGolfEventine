package com.sda.eventine.service;

import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.exception.EventAlreadyExistsException;
import com.sda.eventine.exception.EventNotFoundException;
import com.sda.eventine.model.Event;
import com.sda.eventine.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;


    public void createEvent(EventDTO event) {

        if (event.getName().equals(repository.getEventByName(event.getName()))) {
            throw new EventAlreadyExistsException(String
                    .format("Event with name %s already exists", event.getName()));
        }

        //TODO: implement ACCESSORS CHAIN - lombok accessors
        Event temp = new Event();
        //ACCESSORS (fluent api na setry) - Lombok
        temp.setName(event.getName());
        temp.setDescription(event.getDescription());
        temp.setCreatedAt(LocalDateTime.now());
        temp.setStart(LocalDateTime.parse(event.getStart()));
        temp.setEnd(LocalDateTime.parse(event.getEnd()));
        log.info("Created event: " + temp.getName() + " - starting: " + temp.getStart());
        repository.save(temp);


    }

    //find by id
    public Event findById(Long id) {

        if (repository.findById(id).isEmpty()) {
            throw new EventNotFoundException(String
                    .format("Event with id %d doesn't exist", id));
        }
        return repository.findById(id).get();

    }

    //find all

    public List<Event> findAll() {

        return repository.findAll();

    }

    //find by date

    public List<Event> findByDate(LocalDateTime fromDate, LocalDateTime tillDate) {

        //TODO:
        return repository.findAll().stream()
                .filter(event -> event.getStart().isAfter(fromDate))
                .filter(event -> event.getStart().isBefore(tillDate))
                .collect(Collectors.toList());

    }

    //update

    public void update(Long id, EventDTO event) {

        if (repository.existsById(id)) {
            Event temp = repository.getById(id);
            //ACCESSORS (fluent api na setry) - Lombok
            temp.setName(event.getName());
            temp.setDescription(event.getDescription());
            temp.setCreatedAt(LocalDateTime.now());
            temp.setStart(LocalDateTime.parse(event.getStart()));
            temp.setEnd(LocalDateTime.parse(event.getEnd()));
            log.info(String.format("Updating %s to: " + temp.toString(), repository.getById(id).getName()));
            repository.save(temp);

        } else throw new EventNotFoundException(String
                .format("Event with name %s doesn't exist", event.getName()));
    }

    //delete

    public void delete(Long id) {
        if (repository.existsById(id)) {

            log.info(String.format("Removing event with id %d", id));
            repository.deleteById(id);

        } else throw new EventNotFoundException(String
                .format("Event with id %d doesn't exist", id));


    }

    public List<Event> getEventsByDate(LocalDateTime from, LocalDateTime till) {
        return repository.getEventsByStartBetween(from, till);
    }
}
