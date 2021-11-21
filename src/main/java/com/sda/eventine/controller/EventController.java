package com.sda.eventine.controller;

import com.sda.eventine.dto.BetweenDatesDTO;
import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.model.Event;
import com.sda.eventine.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/events")
public class EventController {


    private final EventService service;


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


}
