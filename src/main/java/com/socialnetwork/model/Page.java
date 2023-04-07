package com.socialnetwork.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="[page]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PAGE")
    @SequenceGenerator(name="S_PAGE", sequenceName = "S_PAGE", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "name")
    private String name;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "cover_image")
    private String coverImage;
}