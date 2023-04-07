package com.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="[chat_user]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CHAT_USER")
    @SequenceGenerator(name="S_CHAT_USER", sequenceName = "S_CHAT_USER", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "un_seen")
    private Long unSeen;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Message> messages = new ArrayList<>();
}
