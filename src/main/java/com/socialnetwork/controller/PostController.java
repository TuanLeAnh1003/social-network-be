package com.socialnetwork.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.dto.PostDto;
import com.socialnetwork.model.Post;
import com.socialnetwork.service.IPostService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {
    private final IPostService postService;

    @PostMapping("/post/save")
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return ResponseEntity.ok().body(postService.savePost(post));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
    }

    @PostMapping("/post")
    public ResponseEntity<List<PostDto>> getPostsByOwnerId(@RequestBody String userId) {
        return ResponseEntity.ok().body(postService.getPostsByOwnerId(Long.parseLong(userId)));
    }

    @PostMapping("/post/delete")
    public ResponseEntity<Integer> deletePost(@RequestBody String postId) {
        return ResponseEntity.ok().body(postService.deletePost(Long.parseLong(postId)));
    }

    @PostMapping("/post/count-share")
    public ResponseEntity<Integer> countShare(@RequestBody String postId) {
        return ResponseEntity.ok().body(postService.countShare(Long.parseLong(postId)));
    }
}