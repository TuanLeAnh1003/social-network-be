package com.socialnetwork.service;

import com.socialnetwork.dto.FriendDto;
import com.socialnetwork.model.Friend;
import com.socialnetwork.model.User;
import com.socialnetwork.repository.FriendRepo;
import com.socialnetwork.repository.UserRepo;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendService implements IFriendService {
    private final FriendRepo friendRepo;
    private final UserRepo userRepo;

    @Override
    public Friend add(Friend friend) {
        return friendRepo.save(friend);
    }

    @Override
    public List<User> getFriendsByUserId(Long userId) {
        List<Friend> friends = friendRepo.findAllByUserId(userId);
        List<User> list = new ArrayList<User>();

        for (Friend friend : friends) {
            Optional<User> userOpt = userRepo.findById(friend.getFriendId());
            if (!userOpt.isPresent()) {
                throw new RuntimeException("No user found with provided id");
            }

            list.add(userOpt.get());
        }

        return list;

    }

    @Override
    public List<User> getFriendsByFriendId(Long friendId) {
        List<Friend> friends = friendRepo.findAllByFriendId(friendId);
        List<User> list = new ArrayList<User>();

        for (Friend friend : friends) {
            Optional<User> userOpt = userRepo.findById(friend.getUserId());
            if (!userOpt.isPresent()) {
                throw new RuntimeException("No user found with provided id");
            }

            list.add(userOpt.get());
        }

        return list;
    }

    @Override
    public FriendDto getFriends(Long userId) {
        List<Friend> friends = friendRepo.findAllByUserId(userId);
        List<User> list = new ArrayList<User>();

        for (Friend friend : friends) {
            Optional<User> userOpt = userRepo.findById(friend.getFriendId());
            if (!userOpt.isPresent()) {
                throw new RuntimeException("No user found with provided id");
            }

            list.add(userOpt.get());
        }

        List<Friend> friends2 = friendRepo.findAllByFriendId(userId);

        for (Friend friend : friends2) {
            Optional<User> userOpt = userRepo.findById(friend.getUserId());
            if (!userOpt.isPresent()) {
                throw new RuntimeException("No user found with provided id");
            }

            list.add(userOpt.get());
        }

        FriendDto friendDto = new FriendDto();
        friendDto.setFriends(list);
        friendDto.setUserId(userId);

        return friendDto;
    }

    @Override
    public Boolean isFriend(Friend friend) {
        Friend check = friendRepo.findByUserIdAndFriendId(friend.getUserId(), friend.getFriendId());
        
        if (check != null) {
            return true;
        } else {
            return false;
        }
    }
}