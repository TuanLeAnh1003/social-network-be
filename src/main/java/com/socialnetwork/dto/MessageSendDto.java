package com.socialnetwork.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MessageSendDto {
    private Long chatId;
    private Long senderId;
    private String content;
    private Timestamp createdAt;
}
