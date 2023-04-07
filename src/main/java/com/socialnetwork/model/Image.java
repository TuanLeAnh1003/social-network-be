package com.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="[image]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_IMAGE")
    @SequenceGenerator(name="S_IMAGE", sequenceName = "S_IMAGE", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "created_at")
    private Timestamp createdAt;
}