package com.socialnetwork.controller;

import com.socialnetwork.dto.MessageDto;
import com.socialnetwork.dto.MessageSendDto;
import com.socialnetwork.model.Chat;
import com.socialnetwork.model.ChatUser;
import com.socialnetwork.model.Message;
import com.socialnetwork.model.User;
import com.socialnetwork.repository.ChatRepo;
import com.socialnetwork.repository.ChatUserRepo;
import com.socialnetwork.service.IChatService;
import com.socialnetwork.service.IMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//  @CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {
	private final IMessageService messageService;
	private final IChatService chatService;
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final ChatUserRepo chatUserRepo;

	@MessageMapping("/send/message/{chatId}")
	@SendTo(value = { "/message/receive/{chatId}" })
	public ResponseEntity<List<MessageDto>> saveMessage(MessageSendDto message, @PathVariable String chatId) throws Exception {
		Message newMessage = new Message();

		ChatUser chatUser = chatUserRepo.findByUserIdAndChatId(message.getSenderId(), message.getChatId());
		newMessage.setChatUserId(chatUser.getId());
		newMessage.setContent(message.getContent());
		newMessage.setCreatedAt(message.getCreatedAt());

		List<ChatUser> otherChatUser = chatUserRepo.findAllByChatId(message.getChatId());

		for (ChatUser chat : otherChatUser) {
			if (chat.getUserId() == message.getSenderId()) {
				chatService.updateSeen(chat.getUserId(), message.getChatId(), Long.valueOf(0));
			} else {
				chatService.updateSeen(chat.getUserId(), message.getChatId(), chat.getUnSeen() + 1);
				simpMessagingTemplate.convertAndSend("/message/notify/" + chat.getUserId(), "Receive message");
			}
		}

		return ResponseEntity.ok().body(messageService.save(newMessage));
	}

}