package com.sda.eventine.controller;

import com.sda.eventine.dto.CommentDTO;
import com.sda.eventine.dto.CommentFacade;
import com.sda.eventine.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/event/{eventId}/comment")
public class CommentController {


    private final CommentService service;


    @PostMapping (value = "",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@PathVariable UUID eventId, @RequestBody CommentDTO commentDTO) {
        service.saveComment(eventId, commentDTO);
    }


    @GetMapping (value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentFacade> getAllComments(@PathVariable UUID eventId) {
        return service.findAllComments(eventId);
    }


}
