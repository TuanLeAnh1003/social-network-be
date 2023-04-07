package com.socialnetwork.repository;

import com.socialnetwork.model.Friend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRepo extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUserId(Long userId);
    List<Friend> findAllByFriendId(Long friendId);
    
    @Query("SELECT f FROM Friend f WHERE (f.userId = :userId AND f.friendId = :friendId) OR (f.userId = :friendId AND f.friendId = :userId)")
    Friend findByUserIdAndFriendId(Long userId, Long friendId);
}