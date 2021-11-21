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
@RequestMapping(value = "/api/events/comments")
public class CommentController {


    private final CommentService service;


    @PutMapping(value = "/{event_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@PathVariable Long event_id, @RequestBody CommentDTO commentDTO) {

        service.saveComment(event_id, commentDTO);

    }

    @GetMapping(value = "/{event_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getAllComments(@PathVariable Long event_id) {
        return service.findAllComments(event_id);
    }


}
