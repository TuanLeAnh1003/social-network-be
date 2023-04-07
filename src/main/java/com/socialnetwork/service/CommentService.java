package com.socialnetwork.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.socialnetwork.dto.CommentDto;
import com.socialnetwork.model.Comment;
import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;
import com.socialnetwork.repository.CommentRepo;
import com.socialnetwork.repository.PostRepo;
import com.socialnetwork.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService implements ICommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @Override
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public List<CommentDto> findAllByPostId(Long postId) {
        List<Comment> newListComment = commentRepo.findAllByPostId(postId);
        List<CommentDto> newListCommentDto = new ArrayList<CommentDto>();

        for (Comment comment : newListComment) {
            CommentDto newCommentDto = new CommentDto();

            newCommentDto.setId(comment.getId());

            Optional<Post> postOpt = postRepo.findById(comment.getPostId());
            if (!postOpt.isPresent()) {
                throw new RuntimeException("No post found with provided id");
            }
            newCommentDto.setPost(postOpt.get());

            Optional<User> userOpt = userRepo.findById(comment.getUserId());
            if (!userOpt.isPresent()) {
                throw new RuntimeException("No user found with provided id");
            }
            newCommentDto.setUser(userOpt.get());

            newCommentDto.setContent(comment.getContent());
            newCommentDto.setCreatedAt(comment.getCreatedAt());
            newCommentDto.setUpdatedAt(comment.getUpdatedAt());

            newListCommentDto.add(newCommentDto);
        }

        return newListCommentDto;
    }

    @Override
    public List<CommentDto> findAllByCommentId(Long commentId) {
        List<Comment> newListComment = commentRepo.findAllByCommentId(commentId);
        List<CommentDto> newListCommentDto = new ArrayList<CommentDto>();

        for (Comment comment : newListComment) {
            CommentDto newCommentDto = new CommentDto();

            newCommentDto.setId(comment.getId());

            Optional<Comment> commentOpt = commentRepo.findById(comment.getCommentId());
            if (!commentOpt.isPresent()) {
                throw new RuntimeException("No comment found with provided id");
            }
            newCommentDto.setComment(commentOpt.get());

            Optional<User> userOpt = userRepo.findById(comment.getUserId());
            if (!userOpt.isPresent()) {
                throw new RuntimeException("No user found with provided id");
            }
            newCommentDto.setUser(userOpt.get());

            newCommentDto.setContent(comment.getContent());
            newCommentDto.setCreatedAt(comment.getCreatedAt());
            newCommentDto.setUpdatedAt(comment.getUpdatedAt());

            newListCommentDto.add(newCommentDto);
        }

        return newListCommentDto;
    }

    @Override
    public Integer getTotalComment(Long postId) {
        List<CommentDto> listCommentByPostId = this.findAllByPostId(postId);
        Integer countListCommentByCommentId = 0;

        for (CommentDto item : listCommentByPostId) {
            List<CommentDto> list = this.findAllByCommentId(item.getId());

            if (list != null) {
                countListCommentByCommentId = countListCommentByCommentId + list.size();
            }
        }

        Integer count = listCommentByPostId.size() + countListCommentByCommentId;

        return count;
    }

}
