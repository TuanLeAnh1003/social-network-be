package com.socialnetwork.service;

import com.socialnetwork.dto.ChatDto;
import com.socialnetwork.model.Chat;
import com.socialnetwork.model.ChatUser;

import java.util.List;

public interface IChatService {
    List<ChatUser> findAllByUserId(Long userId);
    Chat save(Chat chat);
    // Chat findById(Long id);
    Long findChatIdByUserIds(Long userId, Long friendId);
    Integer updateSeen(Long userId, Long chatId, Long unSeen);
    List<ChatDto> getListChatMessage(Long userId);
}
