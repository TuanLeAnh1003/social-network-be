package com.socialnetwork.dto;

import java.util.List;

import com.socialnetwork.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private Long id;
    private List<User> users;
    private Long unSeen;
}
