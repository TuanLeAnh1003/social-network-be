package com.socialnetwork.service;

import com.socialnetwork.enums.ReactEnum;
import com.socialnetwork.model.Like;

public interface ILikeService {
    Like likePostOrComment (Like like);
    Integer dislikePost (Like like);
    Integer dislikeComment (Like like);
    Integer countTotalLikePost(Long postId);
    Integer countTotalLikeComment(Long commentId);
    Like checkIsMyLikeComment(Long userId, Long commentId);
    Like checkIsMyLikePost(Long userId, Long postId);
    Integer updateType(ReactEnum type, Long id);
}
