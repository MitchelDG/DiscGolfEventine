package com.sda.eventine.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDTO {

    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime start;
    private LocalDateTime end;


}
