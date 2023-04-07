package com.socialnetwork.repository;

import com.socialnetwork.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {
    // User login(User user);
    User findByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.username != :username AND (u.firstName LIKE %:search% or u.lastName LIKE %:search%)")
    List<User> findByName(String search, String username);

    @Modifying
    @Query("UPDATE User u SET u.avatar=:avatar WHERE u.id=:id")
    Integer saveAvatar(String avatar, Long id);

    @Modifying
    @Query("UPDATE User u SET u.coverImage=:coverImage WHERE u.id=:id")
    Integer saveCoverImage(String coverImage, Long id);
}
