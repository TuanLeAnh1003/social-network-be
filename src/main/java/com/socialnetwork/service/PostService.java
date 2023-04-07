package com.socialnetwork.service;

import com.socialnetwork.dto.PostDto;
import com.socialnetwork.model.Page;
import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;
import com.socialnetwork.repository.PostRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService implements IPostService {
    private final IUserService userService;
    private final PostRepo postRepo;
    private final IPageService pageService;

    @Override
    public Post savePost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepo.findAllByOrderByCreatedAtDesc();
        List<PostDto> postDtos = new ArrayList<PostDto>();

        for (Post post : posts) {
            User user = userService.getUserById(post.getOwnerId());
            Page page = new Page();
            PostDto sharePost = new PostDto();

            if (post.getPageId() != null) {
                page = pageService.getPageById(post.getPageId());
            }

            if (post.getSharePostId() != null) {
                sharePost = this.getPostDtoById(post.getSharePostId());
            }

            PostDto postDto = new PostDto();

            postDto.setId(post.getId());
            postDto.setOwner(user);
            if (post.getPageId() != null) {
                postDto.setPage(page);
            }
            if (post.getSharePostId() != null) {
                postDto.setSharePost(sharePost);
            }
            postDto.setContent(post.getContent());
            postDto.setCreatedAt(post.getCreatedAt());
            postDto.setUpdatedAt(post.getUpdatedAt());
            postDto.setImages(post.getImages());
            postDto.setVideos(post.getVideos());

            postDtos.add(postDto);
        }

        return postDtos;
    }

    @Override
    public Integer deletePost(Long postId) {
        return postRepo.deletePostById(postId);
    }

    @Override
    public List<PostDto> getPostsByOwnerId(Long ownerId) {
        List<Post> posts = postRepo.findAllByOwnerIdOrderByCreatedAtDesc(ownerId);
        List<PostDto> postDtos = new ArrayList<PostDto>();

        for (Post post : posts) {
            User user = userService.getUserById(post.getOwnerId());
            Page page = new Page();
            PostDto sharePost = new PostDto();

            if (post.getPageId() != null) {
                page = pageService.getPageById(post.getPageId());
            }

            if (post.getSharePostId() != null) {
                sharePost = this.getPostDtoById(post.getSharePostId());
            }

            PostDto postDto = new PostDto();

            postDto.setId(post.getId());
            postDto.setOwner(user);
            if (post.getPageId() != null) {
                postDto.setPage(page);
            }
            if (post.getSharePostId() != null) {
                postDto.setSharePost(sharePost);
            }
            postDto.setContent(post.getContent());
            postDto.setCreatedAt(post.getCreatedAt());
            postDto.setUpdatedAt(post.getUpdatedAt());
            postDto.setImages(post.getImages());
            postDto.setVideos(post.getVideos());

            postDtos.add(postDto);
        }

        return postDtos;
    }

    @Override
    public PostDto getPostDtoById(Long id) {
        Post post = postRepo.getPostById(id);
        User user = userService.getUserById(post.getOwnerId());
        Page page = new Page();
        PostDto sharePost = new PostDto();

        if (post.getPageId() != null) {
            page = pageService.getPageById(post.getPageId());
        }

        if (post.getSharePostId() != null) {
            sharePost = this.getPostDtoById(post.getSharePostId());
        }

        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setOwner(user);
        if (post.getPageId() != null) {
            postDto.setPage(page);
        }
        if (post.getSharePostId() != null) {
            postDto.setSharePost(sharePost);
        }
        postDto.setContent(post.getContent());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setUpdatedAt(post.getUpdatedAt());
        postDto.setImages(post.getImages());
        postDto.setVideos(post.getVideos());

        return postDto;
    }

    @Override
    public Integer countShare(Long postId) {
        List<Post> list = postRepo.findAllBySharePostId(postId);

        return list.size();
    }
}