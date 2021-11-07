package com.sda.eventine.dto;


import com.sda.eventine.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime start;
    private LocalDateTime end;
    private User owner;
    private List<User> participants;

}
