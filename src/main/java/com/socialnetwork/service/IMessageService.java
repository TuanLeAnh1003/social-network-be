package com.socialnetwork.service;

import com.socialnetwork.dto.MessageDto;
import com.socialnetwork.model.Message;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface IMessageService {
    @Query("SELECT m from Message m WHERE m.chat_id=:chatId")
    List<MessageDto> findAllByChatId(Long chatId);

    List<MessageDto> findAllByUserIds(Long userId, Long friendId);

    List<MessageDto> save(Message message);

    Long getChatId(Long userId, Long friendId);
}
