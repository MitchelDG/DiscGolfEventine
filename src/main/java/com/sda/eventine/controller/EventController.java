package com.sda.eventine.controller;

import com.sda.eventine.dto.BetweenDatesDTO;
import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.model.Event;
import com.sda.eventine.model.User;
import com.sda.eventine.service.EventService;
import com.sda.eventine.service.ParticipationService;
import com.sda.eventine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/event")
public class EventController {


    private final EventService service;
    private final UserService userService;
    private final ParticipationService participationService;

    //TODO: implement additional endpoints for search-engine

    @GetMapping(value = "/all")
    public List<Event> findAll() {

        return service.findAll();
    }


    @GetMapping(value = "/{id}")
    public Event getEventById(@PathVariable Long id) {

        return service.findById(id);
    }


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody EventDTO event) {

        service.createEvent(event);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {

        service.update(id, event);
    }


    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {

        service.delete(id);
    }

    @GetMapping(value = "/between", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEventsBetweenDates(@RequestBody BetweenDatesDTO betweenDatesDTO) {

        return service.getEventsByDate(betweenDatesDTO.parseFrom(), betweenDatesDTO.parseTill());
    }

    @PutMapping(value = "/{eventId}/add-user/{userId}")
    public void addParticipant(@PathVariable Long eventId, @PathVariable Long userId) {

        participationService.connect(eventId, userId);

    }


    @GetMapping(value = "/{eventId}/participants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getParticipants(@PathVariable Long eventId) {

       return userService.getParticipants(eventId);

    }

    @GetMapping(value = "/{eventId}/free-space", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getFreeSpaces(@PathVariable Long eventId) {

        return participationService.getFreeSpaces(eventId);
    }

}
