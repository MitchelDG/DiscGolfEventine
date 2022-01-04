package com.sda.eventine.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


@Data
@Accessors(chain = true)
public class CommentFacade {

    private LocalDateTime date;
    private String body;
    private String publisher;

}


