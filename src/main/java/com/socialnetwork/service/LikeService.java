package com.socialnetwork.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.socialnetwork.enums.ReactEnum;
import com.socialnetwork.model.Like;
import com.socialnetwork.repository.LikeRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LikeService implements ILikeService {
    private final LikeRepo likeRepo;

    @Override
    public Like likePostOrComment(Like like) {
        Like find = new Like();
        if (like.getPostId() != null) {
            find = likeRepo.findByUserIdAndPostId(like.getUserId(), like.getPostId());
        }
        if (like.getCommentId() != null) {
            find = likeRepo.findByUserIdAndCommentId(like.getUserId(), like.getCommentId());
        }

        if (find != null) {
            Integer result = likeRepo.updateType(like.getType(), find.getId());
            if (result != null) {
                return find;
            } else {
                return find;
            }
        } else {
            return likeRepo.save(like);
        }
    }

    @Override
    public Integer dislikePost(Like like) {
        return likeRepo.findOneAndDeleteLikePost(like.getUserId(), like.getPostId());
    }

    @Override
    public Integer dislikeComment(Like like) {
        return likeRepo.findOneAndDeleteLikeComment(like.getUserId(), like.getCommentId());
    }

    @Override
    public Integer countTotalLikePost(Long postId) {
        return likeRepo.findAllByPostId(postId).size();
    }

    @Override
    public Integer countTotalLikeComment(Long commentId) {
        List<Like> list = likeRepo.findAllByCommentId(commentId);
        if (list != null) {
            Integer count = list.size();

            return count;
        } else {
            return 0;
        }
    }

    @Override
    public Like checkIsMyLikeComment(Long userId, Long commentId) {
        Like like = likeRepo.findByUserIdAndCommentId(userId, commentId);

        if (like != null) {
            return like;
        } else {
            return null;
        }
    }

    @Override
    public Like checkIsMyLikePost(Long userId, Long postId) {
        Like like = likeRepo.findByUserIdAndPostId(userId, postId);

        if (like != null) {
            return like;
        } else {
            return null;
        }
    }

    @Override
    public Integer updateType(ReactEnum type, Long id) {
        return likeRepo.updateType(type, id);
    }
}
