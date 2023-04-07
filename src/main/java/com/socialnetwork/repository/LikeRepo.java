package com.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.socialnetwork.enums.ReactEnum;
import com.socialnetwork.model.Like;

public interface LikeRepo extends JpaRepository<Like, Long> {
    @Modifying
    @Query("DELETE FROM Like l where l.userId=:userId and l.postId=:postId")
    Integer findOneAndDeleteLikePost(Long userId, Long postId);
    @Modifying
    @Query("DELETE FROM Like l where l.userId=:userId and l.commentId=:commentId")
    Integer findOneAndDeleteLikeComment(Long userId, Long commentId);
    List<Like> findAllByPostId(Long postId);
    List<Like> findAllByCommentId(Long commentId);
    Like findByUserIdAndCommentId(Long userId, Long commentId);
    Like findByUserIdAndPostId(Long userId, Long postId);
    @Modifying
    @Query("UPDATE Like l SET l.type=:type WHERE l.id=:id")
    Integer updateType(ReactEnum type, Long id);
}
