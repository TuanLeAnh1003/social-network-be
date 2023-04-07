package com.socialnetwork.dto;

import com.socialnetwork.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {
    private Long userId;
    private List<User> friends;
}