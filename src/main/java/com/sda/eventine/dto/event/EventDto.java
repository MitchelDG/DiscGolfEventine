package com.sda.eventine.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private UUID id;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime end;
    private Long capacity;
    private String description;
}
