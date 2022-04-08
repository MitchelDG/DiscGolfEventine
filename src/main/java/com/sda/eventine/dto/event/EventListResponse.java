package com.sda.eventine.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventListResponse {

    private List<EventDto> events;
    private Integer page;
    private Integer size;
    private Long count;
    private String sort;
}
