package com.sda.eventine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BetweenDatesDTO {

    private String from;
    private String till;


    public LocalDateTime parseFrom() {
        return LocalDateTime.parse(getFrom());
    }


    public LocalDateTime parseTill() {
        return LocalDateTime.parse(getTill());
    }

}
