package com.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="[video]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_VIDEO")
    @SequenceGenerator(name="S_VIDEO", sequenceName = "S_VIDEO", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "created_at")
    private Timestamp createdAt;
}