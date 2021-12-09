package com.sda.eventine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Accessors(chain = true)
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
