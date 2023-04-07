package com.socialnetwork.dto;

import java.sql.Timestamp;

import com.socialnetwork.model.Comment;
import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Post post;
    private Comment comment;
    private User user;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
