package com.socialnetwork.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.dto.CommentDto;
import com.socialnetwork.model.Comment;
import com.socialnetwork.service.ICommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
    private final ICommentService commentService;

    @PostMapping("/comments/findAllByPostId")
    public ResponseEntity<List<CommentDto>> findAllByPostId(@RequestBody String postId) {
        return ResponseEntity.ok().body(commentService.findAllByPostId(Long.parseLong(postId)));
    }

    @PostMapping("/comments/findAllByCommentId")
    public ResponseEntity<List<CommentDto>> findAllByCommentId(@RequestBody String commentId) {
        return ResponseEntity.ok().body(commentService.findAllByCommentId(Long.parseLong(commentId)));
    }

    @PostMapping("/comment/save")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return ResponseEntity.ok().body(commentService.save(comment));
    }

    @PostMapping("/comments/getTotalComment")
    public ResponseEntity<Integer> getTotalComment(@RequestBody String postId) {
        return ResponseEntity.ok().body(commentService.getTotalComment(Long.parseLong(postId)));
    }
}
