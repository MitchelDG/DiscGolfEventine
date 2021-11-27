package com.sda.eventine.service;

import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.exception.EventAlreadyExistsException;
import com.sda.eventine.exception.EventNotFoundException;
import com.sda.eventine.model.Event;
import com.sda.eventine.model.User;
import com.sda.eventine.repository.CommentRepository;
import com.sda.eventine.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final CommentRepository commentRepo;


    public void createEvent(EventDTO event) {

        //TODO: implement pattern based matching logic

        if (repository.existsByName(event.getName())) {
            throw new EventAlreadyExistsException(String
                    .format("Event with name %s already exists", event.getName()));
        }

        Event temp = Event.builder()
                .name(event.getName())
                .description(event.getDescription())
                .createdAt(LocalDateTime.now())
                .start(LocalDateTime.parse(event.getStart()))
                .end(LocalDateTime.parse(event.getStart()))
                .build();

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



        return repository.getEventsByStartBetween(fromDate, tillDate);


    }

    //update

    public void update(Long id, Event event) {

        if (repository.existsById(id)) {
            Event temp = repository.getById(id);

            temp.setName(event.getName())
            .setDescription(event.getDescription())
            .setCreatedAt(LocalDateTime.now())
            .setStart(event.getStart())
            .setEnd(event.getEnd());

            log.info(String.format("Updating %s  ",repository.getById(id).getName()));

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

    //add participant

    public void addParticipant(Long id, User participant) {

        if (repository.existsById(id)) {

            var temp = repository.getById(id);
            var ppl = temp.getParticipants();
            ppl.add(participant);
            temp.setParticipants(ppl);
            update(id, temp);

        } else throw new EventNotFoundException(String.format("Event with id %d doesn't exist", id));

    }

}
