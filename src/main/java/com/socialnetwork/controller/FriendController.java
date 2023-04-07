package com.socialnetwork.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.dto.FriendDto;
import com.socialnetwork.model.Friend;
import com.socialnetwork.model.User;
import com.socialnetwork.service.IFriendService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FriendController {
    private final IFriendService friendService;

    @PostMapping("/friends")
    public ResponseEntity<FriendDto> getFriends(@RequestBody String userId) {
        return ResponseEntity.ok().body(friendService.getFriends(Long.parseLong(userId)));
    }

    @PostMapping("/friends/userId")
    public ResponseEntity<List<User>> getFriendsByFriendId(@RequestBody String userId) {
        return ResponseEntity.ok().body(friendService.getFriendsByUserId(Long.parseLong(userId)));
    }

    @PostMapping("/friend/checkIsFriend")
    public ResponseEntity<Boolean> checkIsFriend(@RequestBody Friend friend) {
        return ResponseEntity.ok().body(friendService.isFriend(friend));
    }

    @PostMapping("/friend/save")
    public ResponseEntity<Friend> saveFriend(@RequestBody Friend friend) {
        Boolean check = friendService.isFriend(friend);

        if (check) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok().body(friendService.add(friend));
        }
    }
}