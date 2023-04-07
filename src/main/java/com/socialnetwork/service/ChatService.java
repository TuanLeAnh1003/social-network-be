package com.socialnetwork.service;

import com.socialnetwork.dto.ChatDto;
import com.socialnetwork.model.Chat;
import com.socialnetwork.model.ChatUser;
import com.socialnetwork.model.User;
import com.socialnetwork.repository.ChatRepo;
import com.socialnetwork.repository.ChatUserRepo;
import com.socialnetwork.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatService implements IChatService {
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;
    private final ChatUserRepo chatUserRepo;

    @Override
    public List<ChatUser> findAllByUserId(Long userId) {
        return chatUserRepo.findAllByUserId(userId);
    }

    @Override
    public Chat save(Chat chat) {
        return chatRepo.save(chat);
    }

    // @Override
    // public ChatDto findById(Long id) {
    //     List<Chat> list = chatRepo.findAllByChatId(id);
    //     ChatDto chatDto = new ChatDto();
    //     List<User> users = new ArrayList<>();

    //     for (Chat item : list) {
    //         Optional<User> userOpt = userRepo.findById(item.getUserId());
    //         if (!userOpt.isPresent()) {
    //             throw new RuntimeException("No user found with provided id");
    //         }

    //         users.add(userOpt.get());
    //     }

    //     chatDto.setId(list.get(0).getId());
    //     chatDto.setUsers(users);

    //     return chatDto;
    // }

    @Override
    public Long findChatIdByUserIds(Long userId, Long friendId) {
        List<ChatUser> list1 = chatUserRepo.findAllByUserId(userId);

        for (ChatUser chat1 : list1) {
            List<ChatUser> findChat = chatUserRepo.findAllByChatId(chat1.getChatId());
            for (ChatUser chat2 : findChat) {
                if (chat2.getUserId() == friendId) {
                    return chat2.getChatId();
                }
            }
        }

        return null;
    }

    @Override
    public Integer updateSeen(Long userId, Long chatId, Long unSeen) {
        return chatUserRepo.updateSeen(userId, chatId, unSeen);
    }

    @Override
    public List<ChatDto> getListChatMessage(Long userId) {
        log.info("userId: {}", userId);
        List<ChatUser> listChatUser = chatUserRepo.findAllByUserId(userId);
        List<ChatDto> listFriendChat = new ArrayList<ChatDto>();

        for (ChatUser item : listChatUser) {
            List<ChatUser> newList = chatUserRepo.findAllByChatId(item.getChatId());
            ChatDto chatDto = new ChatDto();
            List<User> listUser = new ArrayList<User>();

            for (ChatUser it : newList) {
                if (it.getUserId() == userId) {
                    chatDto.setId(it.getChatId());
                    chatDto.setUnSeen(it.getUnSeen());
                } else {
                    Optional<User> userOpt = userRepo.findById(it.getUserId());

                    listUser.add(userOpt.get());
                }
            }

            chatDto.setUsers(listUser);
            listFriendChat.add(chatDto);
        }
        
        return listFriendChat;
    }
}