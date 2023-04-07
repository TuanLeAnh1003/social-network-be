package com.socialnetwork.repository;

import com.socialnetwork.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat, Long> {
}
