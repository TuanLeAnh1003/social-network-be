package com.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="[friend]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FRIEND")
    @SequenceGenerator(name="S_FRIEND", sequenceName = "S_FRIEND", allocationSize=1, initialValue = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "friend_id")
    private Long friendId;
}