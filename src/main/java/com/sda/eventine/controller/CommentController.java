package com.sda.eventine.controller;

import com.sda.eventine.dto.CommentDTO;
import com.sda.eventine.model.Comment;
import com.sda.eventine.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/event/{eventId}/comment")
public class CommentController {


    private final CommentService service;


    @PostMapping (value = "",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@PathVariable Long eventId, @RequestBody CommentDTO commentDTO) {
        service.saveComment(eventId, commentDTO);

    }


    @GetMapping (value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getAllComments(@PathVariable Long eventId) {
        return service.findAllComments(eventId);
    }


}
