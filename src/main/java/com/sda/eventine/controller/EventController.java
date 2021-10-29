package com.sda.eventine.controller;

import com.sda.eventine.exception.NotFoundException;
import com.sda.eventine.model.Event;
import com.sda.eventine.repository.EventRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.BadAttributeValueExpException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }


    //TODO: implement aditional endpoints for searchengine

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> findAll() {
        Iterable<Event> events = repository.findAll();

        List<Event> eventList = StreamSupport.stream(events.spliterator(), false).collect(Collectors.toList());

        return ResponseEntity.ok(eventList);
    }


    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Optional<Event> optionalEvent = repository.findById(id);

        if (optionalEvent.isEmpty()) {
            throw new NotFoundException(String.format("Condition with id %d not found", id));
        }
        return ResponseEntity.ok(optionalEvent.get());
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event record = repository.save(event);

        return ResponseEntity.ok(record);
    }


    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        Event record = repository.save(event);

        return ResponseEntity.ok(record);
    }

    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> deleteEvent(@PathVariable Integer id) {
        Optional<Event> optionalEvent = repository.findById(id);

        if (optionalEvent.isEmpty()) {
            throw new NotFoundException(String.format("Condition with id %d not found", id));
        }
        repository.deleteById(id);

        return ResponseEntity.ok(optionalEvent.get());
    }




}
