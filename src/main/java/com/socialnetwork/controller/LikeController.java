package com.socialnetwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.model.Like;
import com.socialnetwork.service.ILikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LikeController {
    private final ILikeService likeService;

    @PostMapping("/like/like-post-or-comment")
    public ResponseEntity<Like> likePostOrComment(@RequestBody Like like) {
        return ResponseEntity.ok().body(likeService.likePostOrComment(like));
    }

    @PostMapping("/like/dislike-post")
    public ResponseEntity<Integer> dislikePost(@RequestBody Like like) {
        return ResponseEntity.ok().body(likeService.dislikePost(like));
    }

    @PostMapping("/like/dislike-comment")
    public ResponseEntity<Integer> dislikeComment(@RequestBody Like like) {
        return ResponseEntity.ok().body(likeService.dislikeComment(like));
    }

    @PostMapping("/like/count-like-post")
    public ResponseEntity<Integer> countLikePost(@RequestBody String postId) {
        return ResponseEntity.ok().body(likeService.countTotalLikePost(Long.parseLong(postId)));
    }

    @PostMapping("/like/count-like-comment")
    public ResponseEntity<Integer> countLikeComment(@RequestBody String commentId) {
        int count = likeService.countTotalLikeComment(Long.parseLong(commentId));
        return ResponseEntity.ok().body(count);
    }

    @PostMapping("/like/is-my-like-comment")
    public ResponseEntity<Like> isMyLikeComment(@RequestBody Like like) {
        return ResponseEntity.ok().body(likeService.checkIsMyLikeComment(like.getUserId(), like.getCommentId()));
    }

    @PostMapping("/like/is-my-like-post")
    public ResponseEntity<Like> isMyLikePost(@RequestBody Like like) {
        return ResponseEntity.ok().body(likeService.checkIsMyLikePost(like.getUserId(), like.getPostId()));
    }
}
