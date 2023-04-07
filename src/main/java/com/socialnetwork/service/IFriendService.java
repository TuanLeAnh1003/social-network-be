package com.socialnetwork.service;

import com.socialnetwork.model.Friend;
import com.socialnetwork.model.User;

import java.util.List;

import com.socialnetwork.dto.FriendDto;

public interface IFriendService {
    Friend add(Friend friend);

    List<User> getFriendsByUserId(Long userId);

    List<User> getFriendsByFriendId(Long friendId);

    FriendDto getFriends(Long userId);

    Boolean isFriend (Friend friend);
}