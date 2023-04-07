package com.socialnetwork.repository;

import com.socialnetwork.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.chatUserId=:chatUserId ORDER BY m.createdAt ASC")
    List<Message> findAllByChatUserIdOrderByCreatedAtAsc(Long chatUserId);
}
