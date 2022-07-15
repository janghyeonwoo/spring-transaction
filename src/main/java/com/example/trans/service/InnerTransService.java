package com.example.trans.service;

import com.example.trans.dto.ReqGaveSave;
import com.example.trans.entity.Game;
import com.example.trans.entity.GameHistory;
import com.example.trans.repository.GameHistoryRepository;
import com.example.trans.repository.GameRepo;
import com.example.trans.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InnerTransService {
    private final GameHistoryRepository gameHistoryRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public void saveGameHistory(Game game, String content){
        GameHistory gameHistory = GameHistory.builder()
                .game(game)
                .content(content)
                .build();
        gameHistoryRepository.save(gameHistory);
        throw new RuntimeException();
    }
}
