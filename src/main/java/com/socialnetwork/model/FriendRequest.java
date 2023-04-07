package com.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="[friend_request]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FRIEND_REQUEST")
    @SequenceGenerator(name="S_FRIEND_REQUEST", sequenceName = "S_FRIEND_REQUEST", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "receiver_id")
    private Long receiverId;
}