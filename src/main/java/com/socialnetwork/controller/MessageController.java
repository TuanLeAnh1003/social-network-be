package com.socialnetwork.controller;

import com.socialnetwork.dto.ChatDto;
import com.socialnetwork.dto.MessageDto;
import com.socialnetwork.model.Chat;
import com.socialnetwork.model.ChatUser;
import com.socialnetwork.model.Friend;
import com.socialnetwork.model.Message;
import com.socialnetwork.service.IChatService;
import com.socialnetwork.service.IMessageService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageController {
    private final IMessageService messageService;
    private final IChatService chatService;

    @PostMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesByChatId(@RequestBody String chatId) {
        return ResponseEntity.ok().body(messageService.findAllByChatId(Long.parseLong(chatId)));
    }

    @PostMapping("/messages-by-user-ids")
    public ResponseEntity<List<MessageDto>> getMessagesByUserIds(@RequestBody Friend friend) {
        return ResponseEntity.ok().body(messageService.findAllByUserIds(friend.getUserId(), friend.getFriendId()));
    }

    // @PostMapping("/message/save")
    // public ResponseEntity<List<MessageDto>> saveMessage(@RequestBody Message message) {
    //     return ResponseEntity.ok().body(messageService.save(message));
    // }

    @PostMapping("/message/getChatId")
    public ResponseEntity<Long> getChatId(@RequestBody Friend friend) {
        return ResponseEntity.ok().body(messageService.getChatId(friend.getUserId(), friend.getFriendId()));
    }

    @PostMapping("/chat/getListChatMessage")
    public ResponseEntity<List<ChatDto>> getListChatMessage(@RequestBody String userId) {
        return ResponseEntity.ok().body(chatService.getListChatMessage(Long.parseLong(userId)));
    }

    @PostMapping("/chat/updateSeen")
    public ResponseEntity<Integer> updateSeen(@RequestBody ChatUser chat) {
        return ResponseEntity.ok().body(chatService.updateSeen(chat.getUserId(), chat.getChatId(), chat.getUnSeen()));
    }
}