package com.socialnetwork.service;

import com.socialnetwork.dto.MessageDto;
import com.socialnetwork.model.Chat;
import com.socialnetwork.model.ChatUser;
import com.socialnetwork.model.Message;
import com.socialnetwork.model.User;
import com.socialnetwork.repository.ChatUserRepo;
import com.socialnetwork.repository.MessageRepo;
import com.socialnetwork.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageService implements IMessageService {
    private final MessageRepo messageRepo;
    private final IChatService chatService;
    private final UserRepo userRepo;
    private final ChatUserRepo chatUserRepo;

    @Override
    public List<MessageDto> findAllByChatId(Long chatId) {
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();
        List<ChatUser> list = chatUserRepo.findAllByChatId(chatId);

        for (ChatUser chatUser : list) {
            List<Message> messages = messageRepo.findAllByChatUserIdOrderByCreatedAtAsc(chatUser.getId());
    
            for (Message message : messages) {
                Optional<User> userOpt = userRepo.findById(chatUser.getUserId());
                if (!userOpt.isPresent()) {
                    throw new RuntimeException("No user found with provided id");
                }
    
                MessageDto messageDto = new MessageDto();
    
                messageDto.setChatId(chatId);
                messageDto.setSender(userOpt.get());
                messageDto.setContent(message.getContent());
                messageDto.setCreatedAt(message.getCreatedAt());
                messageDto.setUpdatedAt(message.getUpdatedAt());
    
                messageDtos.add(messageDto);
            }
        }

        messageDtos.sort((a,b) -> {
            return a.compareTo(b);
        });

        return messageDtos;
    }

    @Override
    public List<MessageDto> findAllByUserIds(Long userId, Long friendId) {
        Long chatId = chatService.findChatIdByUserIds(userId, friendId);
        List<ChatUser> list = chatUserRepo.findAllByChatId(chatId);
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();

        if (chatId != null) {
            for (ChatUser chatUser : list) {
                List<Message> messages = messageRepo.findAllByChatUserIdOrderByCreatedAtAsc(chatUser.getId());
    
                for (Message message : messages) {
                    Optional<User> userOpt = userRepo.findById(chatUser.getUserId());
                    if (!userOpt.isPresent()) {
                        throw new RuntimeException("No user found with provided id");
                    }
    
                    MessageDto messageDto = new MessageDto();
    
                    messageDto.setChatId(chatId);
                    messageDto.setSender(userOpt.get());
                    messageDto.setContent(message.getContent());
                    messageDto.setCreatedAt(message.getCreatedAt());
                    messageDto.setUpdatedAt(message.getUpdatedAt());
    
                    messageDtos.add(messageDto);
                }
    
            }

            messageDtos.sort((a,b) -> {
                return a.compareTo(b);
            });

            return messageDtos;
        } else {
            // log.info("call when user and friend dont have chat before");
            // Chat newChat1 = new Chat();
            // Chat newChat2 = new Chat();

            // newChat1.setUserId(userId);
            // Long id = chatService.save(newChat1).getId();

            // newChat2.setUserId(userId);
            // newChat2.setId(id);
            // chatService.save(newChat2);

            return messageDtos;
        }
    }

    @Override
    public List<MessageDto> save(Message message) {
        messageRepo.save(message);
        Optional<ChatUser> chatUser = chatUserRepo.findById(message.getChatUserId());

        return this.findAllByChatId(chatUser.get().getChatId());
    }

    @Override
    public Long getChatId(Long userId, Long friendId) {
        log.info("userId: {}", userId);

        log.info("friendId: {}", friendId);

        Long chatId = chatService.findChatIdByUserIds(userId, friendId);
        log.info("chatID: {}", chatId);
        if (chatId != null) {
            return chatId;
        } else {
            Chat newChat = new Chat();
            chatService.save(newChat);

            ChatUser chatUser1 = new ChatUser();
            chatUser1.setChatId(newChat.getId());
            chatUser1.setUserId(userId);
            chatUser1.setUnSeen(Long.valueOf(0));
            chatUserRepo.save(chatUser1);

            ChatUser chatUser2 = new ChatUser();
            chatUser2.setChatId(newChat.getId());
            chatUser2.setUserId(friendId);
            chatUser2.setUnSeen(Long.valueOf(0));
            chatUserRepo.save(chatUser2);

            return newChat.getId();
        }
    }
}