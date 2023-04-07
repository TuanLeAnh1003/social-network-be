package com.socialnetwork.dto;

import com.socialnetwork.model.Image;
import com.socialnetwork.model.Page;
import com.socialnetwork.model.User;
import com.socialnetwork.model.Video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private User owner;
    private Page page;
    private PostDto sharePost;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Collection<Image> images;
    private Collection<Video> videos;
}