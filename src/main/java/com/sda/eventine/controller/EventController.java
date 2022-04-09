package com.sda.eventine.controller;

import com.sda.eventine.dto.BetweenDatesDTO;
import com.sda.eventine.dto.EventCreateDto;
import com.sda.eventine.dto.UserFacade;
import com.sda.eventine.dto.event.EventDto;
import com.sda.eventine.dto.event.EventListResponse;
import com.sda.eventine.model.Event;
import com.sda.eventine.service.EventService;
import com.sda.eventine.service.ParticipationService;
import com.sda.eventine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/event")
public class EventController {


    private final EventService service;
    private final UserService userService;
    private final ParticipationService participationService;

    //TODO: implement additional endpoints for search-engine

    @GetMapping(value = "/all")
    public EventListResponse findAll(Pageable pageable) {
        return service.findAll(pageable);
    }


    @GetMapping(value = "/{id}")
    public EventDto getEventById(@PathVariable UUID id) {
        return service.findById(id);
    }


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody EventCreateDto event) {
        service.createEvent(event);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEvent(@PathVariable UUID id, @RequestBody Event event) {
        service.update(id, event);
    }


    @DeleteMapping(value = "/delete/{id}")
    public void deleteEvent(@PathVariable UUID id) {
        service.delete(id);
    }


    @GetMapping(value = "/between", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEventsBetweenDates(@RequestBody BetweenDatesDTO betweenDatesDTO) {
        return service.getEventsByDate(betweenDatesDTO.parseFrom(), betweenDatesDTO.parseTill());
    }


    @PutMapping(value = "/{eventId}/add-user/{userId}")
    public void addParticipant(@PathVariable UUID eventId, @PathVariable UUID userId) {
        participationService.connect(eventId, userId);
    }


    @GetMapping(value = "/{eventId}/participants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserFacade> getParticipants(@PathVariable UUID eventId) {
        return userService.getParticipants(eventId);
    }


    @GetMapping(value = "/{eventId}/free-space", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getFreeSpaces(@PathVariable UUID eventId) {
        return participationService.getFreeSpaces(eventId);
    }

}

