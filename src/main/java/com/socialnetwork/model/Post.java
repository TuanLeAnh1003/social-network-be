package com.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="[post]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_POST")
    @SequenceGenerator(name="S_POST", sequenceName = "S_POST", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "page_id")
    private Long pageId;
    @Column(name = "share_post_id")
    private Long sharePostId;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Collection<Image> images = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Collection<Video> videos = new ArrayList<>();
}