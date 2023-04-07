package com.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="[message]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MESSAGE")
    @SequenceGenerator(name="S_MESSAGE", sequenceName = "S_MESSAGE", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "chat_user_id")
    private Long chatUserId;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}