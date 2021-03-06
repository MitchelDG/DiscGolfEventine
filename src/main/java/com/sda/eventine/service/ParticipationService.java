package com.sda.eventine.service;


import com.sda.eventine.exception.EventCapacityException;
import com.sda.eventine.exception.UserAlreadyRegisteredException;
import com.sda.eventine.model.Participation;
import com.sda.eventine.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final EventService eventService;


    public void connect(UUID eventId, UUID userId) {

        if (participationRepository.existsByEventIdAndUserId(eventId, userId)) {
            throw new UserAlreadyRegisteredException("User with this id is already signed to this event");

        } else if (getUsersForEvent(eventId).size() >= eventService.findById(eventId).getCapacity()) {
            throw new EventCapacityException("Event is full");

        } else {
            var participation = new Participation()
                    .setEventId(eventId)
                    .setUserId(userId);

            participationRepository.save(participation);
            log.info("User with id {} signed to event with id {}", userId, eventId);
        }
    }


    public List<UUID> getEventsForUser(UUID userId) {
        return participationRepository.getEventsByUserId(userId);
    }


    public List<UUID> getUsersForEvent(UUID eventId) {
        return participationRepository.getUsersByEventId(eventId);
    }


    public Integer getFreeSpaces(UUID eventId) {
        var capacity = eventService.findById(eventId).getCapacity();
        var occupied = getUsersForEvent(eventId).size();
        return capacity.intValue() - occupied;
    }
}