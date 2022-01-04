package com.sda.eventine.repository;

import com.sda.eventine.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c WHERE c.eventId = ?1 ORDER BY c.id ASC")
    List<Comment> findAllByEventId(Long eventId);
}