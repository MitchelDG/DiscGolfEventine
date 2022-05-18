package com.sda.eventine.service;

import com.sda.eventine.dto.EventCreateDto;
import com.sda.eventine.dto.event.EventDto;
import com.sda.eventine.dto.event.EventListResponse;
import com.sda.eventine.exception.EventAlreadyExistsException;
import com.sda.eventine.exception.EventNotFoundException;
import com.sda.eventine.model.Event;
import com.sda.eventine.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final CustomUserDetailsService userDetailsService;
    private final EventRepository repository;
    private static final String EVENT_ID_NOT_FOUND_MSG = "Event with id %d doesn't exist";
    private static final String EVENT_NAME_NOT_FOUND_MSG = "Event with name %s doesn't exist";


    public void createEvent(EventCreateDto event) {

        //TODO: implement pattern based matching logic, which states if event doesn't exist
        if (repository.existsByName(event.getName())) {
            throw new EventAlreadyExistsException(String.format(EVENT_NAME_NOT_FOUND_MSG, event.getName()));
        }
        var temp = new Event();
        temp
                        .setName(event.getName())
                        .setDescription(event.getDescription())
                        .setCapacity(event.getCapacity())
                        .setCreatedAt(LocalDateTime.now())
                        .setStart(LocalDateTime.parse(event.getStart(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .setEnd(LocalDateTime.parse(event.getEnd(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .setCreatedBy("LoggedInUser");
        repository.save(temp);
        log.info("Created event: {} - starting: {}", temp.getName(), temp.getStart());
    }


    public EventDto findById(UUID id) {

        return repository.findById(id).map(event -> EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .capacity(event.getCapacity())
                .start(event.getStart())
                .end(event.getEnd())
                .description(event.getDescription())
                .build()).orElseThrow(() -> {
            log.info("Event with id - {} not found", id);
            throw new EventNotFoundException("Event not found");
        });
    }

    public EventListResponse findAll(Pageable pageable) {


        return EventListResponse.builder()
                .events(repository.findAll(pageable).toList().stream().map(event ->
                        EventDto.builder()
                                .id(event.getId())
                                .name(event.getName())
                                .capacity(event.getCapacity())
                                .start(event.getStart())
                                .end(event.getEnd())
                                .description(event.getDescription())
                                .createdAt(event.getCreatedAt())
                                .build()).collect(Collectors.toList()))
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .sort(pageable.getSort().toString())
                .build();
    }


    public List<Event> findByDate(LocalDateTime fromDate, LocalDateTime tillDate) {
        return repository.getEventsByStartBetween(fromDate, tillDate);
    }


    public void update(UUID id, Event event) {

        if (!repository.existsById(id)) {
            throw new EventNotFoundException(String.format(EVENT_NAME_NOT_FOUND_MSG, event.getName()));

        } else {

            var temp = repository.getById(id);
            temp.setName(event.getName())
                    .setDescription(event.getDescription())
                    .setCreatedAt(LocalDateTime.now())
                    .setStart(event.getStart())
                    .setEnd(event.getEnd());

            repository.save(temp);
            log.info(String.format("Updating %s  ", repository.getById(id).getName()));
        }
    }


    public void delete(UUID id) {

        if (repository.existsById(id)) {

            log.info(String.format("Removing event with id %s", id));
            repository.deleteById(id);

        } else throw new EventNotFoundException(String.format(EVENT_NAME_NOT_FOUND_MSG, id));

    }


    public List<Event> getEventsByDate(LocalDateTime from, LocalDateTime till) {
        return repository.getEventsByStartBetween(from, till);
    }
}
