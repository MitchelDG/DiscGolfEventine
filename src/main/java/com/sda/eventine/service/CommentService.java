package com.sda.eventine.service;

import com.sda.eventine.dto.CommentDTO;
import com.sda.eventine.dto.CommentFacade;
import com.sda.eventine.exception.EmptyCommentException;
import com.sda.eventine.model.Comment;
import com.sda.eventine.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepo;
    private final UserService userService;
    /**
     *
     * @param eventId - should come from controller as id of currently opened Event
     * @param commentDTO - commentDTO.body
     */
    public void saveComment(UUID eventId, CommentDTO commentDTO) {

        if (!commentDTO.getBody().isBlank()) {
            var comment = new Comment();
            comment
                    .setBody(commentDTO.getBody())
                    .setCreatedAt(LocalDateTime.now())
                    .setUserId(/*commentDTO.getPublisherId()*/UUID.randomUUID())
                    .setEventId(eventId);

            commentRepo.save(comment);
            log.info("Comment saved under event with id {}", eventId);

        } else throw new EmptyCommentException("Can not publish an empty comment");
    }


    public List<CommentFacade> findAllComments(UUID eventId) {
       var comments = commentRepo.findAllByEventId(eventId);
       var facades = new LinkedList<CommentFacade>();
       for (Comment comment : comments) {
           facades.add(commentFacade(comment));
       }

       return facades;
    }


    public String getPublisherName(Comment comment) {
        var publisher = userService.findById(comment.getUserId());
        return publisher.getName();
    }


    private CommentFacade commentFacade(Comment comment) {
        return new CommentFacade()
                .setDate(comment.getCreatedAt())
                .setBody(comment.getBody())
                .setPublisher(getPublisherName(comment));
        }
}
