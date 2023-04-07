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
@Table(name="[chat]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CHAT")
    @SequenceGenerator(name="S_CHAT", sequenceName = "S_CHAT", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private Long name;
    @Column(name = "image")
    private Long image;
    
    @JoinTable(name="chat_user",
            joinColumns={@JoinColumn(name="chat_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")}
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users = new ArrayList<>();
}
