package com.sda.eventine.service;


import com.sda.eventine.exception.EventCapacityException;
import com.sda.eventine.exception.UserAlreadyRegisteredException;
import com.sda.eventine.model.Participation;
import com.sda.eventine.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final EventService eventService;

    public void connect(Long eventId, Long userId) {

        if (participationRepository.existsByEventIdAndUserId(eventId, userId)) {

            throw new UserAlreadyRegisteredException(String
                    .format("User with id %d is already signed to event with id %d", userId, eventId));

        } else if (getUsersForEvent(eventId).size() >= eventService.findById(eventId).getCapacity()) {

            throw new EventCapacityException(String.format("Event with id: %d is full", eventId));

        } else {
            var participation = Participation.builder()
                    .eventId(eventId)
                    .userId(userId)
                    .build();
            participationRepository.save(participation);
            log.info(String.format("User with id %d signed to event with id %d", userId, eventId));
        }

    }


    public List<Long> getEventsForUser(Long userId) {

        return participationRepository.getEventsByUserId(userId);

    }


    public List<Long> getUsersForEvent(Long eventId) {

        return participationRepository.getUsersByEventId(eventId);

    }


    public Integer getFreeSpaces(Long eventId) {
        var capacity = eventService.findById(eventId).getCapacity();
        var occupied = getUsersForEvent(eventId).size();

        return capacity.intValue() - occupied;

    }


}