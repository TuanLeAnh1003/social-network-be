package com.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.socialnetwork.enums.ReactEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="[like]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_LIKE")
    @SequenceGenerator(name="S_LIKE", sequenceName = "S_LIKE", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "type")
    private ReactEnum type;
}
