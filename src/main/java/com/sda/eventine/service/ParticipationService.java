package com.sda.eventine.service;


import com.sda.eventine.model.Participation;
import com.sda.eventine.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;


    public void connect(Long eventId, Long userId) {

        var participation = Participation.builder()
                .eventId(eventId)
                .userId(userId)
                .build();
        participationRepository.save(participation);

    }


    public List<Long> getEventsForUser(Long userId) {

        return participationRepository.getEventsByUserId(userId);

    }


    public List<Long> getUsersForEvent(Long eventId) {

        return participationRepository.getUsersByEventId(eventId);

    }

}