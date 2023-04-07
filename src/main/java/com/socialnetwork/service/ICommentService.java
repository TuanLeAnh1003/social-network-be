package com.socialnetwork.service;

import java.util.List;

import com.socialnetwork.dto.CommentDto;
import com.socialnetwork.model.Comment;

public interface ICommentService {
    Comment save(Comment comment);
    List<CommentDto> findAllByPostId(Long postId);
    List<CommentDto> findAllByCommentId(Long commentId);
    Integer getTotalComment(Long postId);
}
