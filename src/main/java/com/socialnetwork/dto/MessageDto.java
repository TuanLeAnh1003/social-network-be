package com.socialnetwork.dto;

import com.socialnetwork.model.User;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MessageDto implements Comparable<MessageDto> {
    private Long chatId;
    private User sender;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public int compareTo(MessageDto o) {
        return getCreatedAt().compareTo(o.getCreatedAt());
    }
}