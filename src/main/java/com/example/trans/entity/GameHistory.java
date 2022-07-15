package com.example.trans.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @JoinColumn(name = "game_idx")
    @ManyToOne
    Game game;

    @Column(name = "content")
    private String content;
}
