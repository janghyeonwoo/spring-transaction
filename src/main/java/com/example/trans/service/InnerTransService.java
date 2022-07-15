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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InnerTransService {
    private final PlatformTransactionManager platformTransactionManager;
    private final GameHistoryRepository gameHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveGameHistory(Game game, String content){
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("Inner Transaction isRollbackOnly : {}" , status.isRollbackOnly());
        GameHistory gameHistory = GameHistory.builder()
                .game(game)
                .content(content)
                .build();
        gameHistoryRepository.save(gameHistory);
        throw new RuntimeException();
    }
}
