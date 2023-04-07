package com.socialnetwork.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="[comment]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_COMMENT")
    @SequenceGenerator(name="S_COMMENT", sequenceName = "S_COMMENT", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
