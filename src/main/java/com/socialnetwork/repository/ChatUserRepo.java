package com.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.socialnetwork.model.ChatUser;

public interface ChatUserRepo extends JpaRepository<ChatUser, Long> {
    @Query("SELECT c FROM ChatUser c WHERE c.userId=:userId")
    List<ChatUser> findAllByUserId(Long userId);
    
    @Query("SELECT c FROM ChatUser c WHERE c.chatId=:chatId")
    List<ChatUser> findAllByChatId(Long chatId);

    ChatUser findByUserIdAndChatId(Long userId, Long chatId);

    @Modifying
    @Query("UPDATE ChatUser c SET c.unSeen=:unSeen WHERE c.userId=:userId AND c.chatId=:chatId")
    Integer updateSeen(Long userId, Long chatId, Long unSeen);
}
