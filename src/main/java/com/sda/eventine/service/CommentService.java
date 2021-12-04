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
    private final UserService userService;
    /**
     *
     * @param eventId - should come from controller as id of currently opened Event
     * @param commentDTO - commentDTO.body
     */
    public void saveComment(Long eventId, CommentDTO commentDTO) {

        if (!commentDTO.getBody().isBlank()) {
            var comment = Comment.builder()
                    .body(commentDTO.getBody())
                    .createdAt(LocalDateTime.now())
                    .publisherId(commentDTO.getPublisherId())
                    .eventId(eventId)
                    .build();

            commentRepo.save(comment);
            log.info(String.format("Comment saved under event with id %d", eventId));

        } else throw new EmptyCommentException("Cannot publish empty comment");

    }


    public List<Comment> findAllComments(Long eventId) {
        return commentRepo.findAllByEventId(eventId);
    }
}
