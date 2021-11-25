package com.sda.eventine.service;

import com.sda.eventine.dto.CommentDTO;
import com.sda.eventine.exception.EmptyCommentException;
import com.sda.eventine.model.Comment;
import com.sda.eventine.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class CommentService {

    private final CommentRepository commentRepo;
    private final EventService eService;
    /**
     *
     * @param eventId - should come from controller as id of currently opened Event
     * @param commentDTO - commentDTO.body
     */
    public void saveComment(Long eventId, CommentDTO commentDTO) {

        if (!commentDTO.getBody().isBlank()) {
            var event = eService.findById(eventId);

            List<Comment> comments = event.comments();
            var comment =new Comment(
                    commentDTO.getBody(),
                    event.owner(),
                    event
            );
            comment.setCreatedAt(LocalDateTime.now());
            comments.add(comment);
            event.comments(comments);

            commentRepo.save(comment);
            eService.update(eventId, event);
            log.info(String.format("Comment saved under event with id %d", eventId));

        } else throw new EmptyCommentException("Cannot publish empty comment");

    }


    public List<Comment> findAllComments(Long eventId) {

        return commentRepo.findAllByEventId(eventId);

    }
}
