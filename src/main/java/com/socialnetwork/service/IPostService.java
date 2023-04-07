package com.socialnetwork.service;

import com.socialnetwork.dto.PostDto;
import com.socialnetwork.model.Post;

import java.util.List;

public interface IPostService {
    Post savePost(Post post);

    List<PostDto> getPosts();

    PostDto getPostDtoById(Long id);

    List<PostDto> getPostsByOwnerId(Long ownerId);

    Integer deletePost(Long postId);

    Integer countShare(Long postId);
}
