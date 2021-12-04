package com.sda.eventine.service;

import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.exception.EventAlreadyExistsException;
import com.sda.eventine.exception.EventNotFoundException;
import com.sda.eventine.model.Event;
import com.sda.eventine.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final CustomUserDetailsService userDetailsService;
    private final EventRepository repository;
    private static final String EVENT_ID_NOT_FOUND_MSG = "Event with id %d doesn't exist";
    private static final String EVENT_NAME_NOT_FOUND_MSG = "Event with name %s doesn't exist";


    public void createEvent(EventDTO event) {

        //TODO: implement pattern based matching logic, which states if event doesn't exist

        if (repository.existsByName(event.getName())) {
            throw new EventAlreadyExistsException(String.format(EVENT_NAME_NOT_FOUND_MSG, event.getName()));
        }

        Event temp = Event.builder()
                .name(event.getName())
                .description(event.getDescription())
                .capacity(event.getCapacity())
                .createdAt(LocalDateTime.now())
                .start(LocalDateTime.parse(event.getStart()))
                .end(LocalDateTime.parse(event.getStart()))
//                .owner(userDetailsService.getCurrentUserName())
                .build();

        log.info("Created event: " + temp.getName() + " - starting: " + temp.getStart());

        repository.save(temp);


    }

    //find by id
    public Event findById(Long id) {

        if (repository.findById(id).isEmpty()) {

            throw new EventNotFoundException(String.format(EVENT_ID_NOT_FOUND_MSG, id));

        } else {

            return repository.findById(id).get();
        }

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

    //delete

    public void delete(Long id) {

        if (repository.existsById(id)) {

            log.info(String.format("Removing event with id %d", id));
            repository.deleteById(id);

        } else throw new EventNotFoundException(String.format(EVENT_NAME_NOT_FOUND_MSG, id));

    }


    public List<Event> getEventsByDate(LocalDateTime from, LocalDateTime till) {

        return repository.getEventsByStartBetween(from, till);

    }

}
