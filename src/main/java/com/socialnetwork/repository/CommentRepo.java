package com.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialnetwork.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByCommentId(Long postId);
}
