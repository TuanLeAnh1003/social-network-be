package com.socialnetwork.repository;

import com.socialnetwork.model.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllBySharePostId(Long sharePostId);
    Post getPostById(Long id);

    @Modifying
    @Query("DELETE FROM Post p WHERE p.id=:postId")
    Integer deletePostById(Long postId);

    List<Post> findAllByOwnerIdOrderByCreatedAtDesc(Long ownerId);
}