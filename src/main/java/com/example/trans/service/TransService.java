package com.example.trans.service;

import com.example.trans.dto.ReqGaveSave;
import com.example.trans.entity.Game;
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
public class TransService {
    private final InnerTransService innerTransService;
    private final GameRepository gameRepository;
    private final PlatformTransactionManager platformTransactionManager;


    //게임의 전체 목록 조회
    public List<Game> getGameAllList(){
        return gameRepository.findAll();
    }

    //게임 저장
    @Transactional
    public void saveGame(ReqGaveSave reqGaveSave){
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("First isRollbackOnly : {}" , status.isRollbackOnly());
        Game game = Game.builder().name(reqGaveSave.getName()).build();
        gameRepository.save(game);
        try {
            innerTransService.saveGameHistory(game, "등록");
        } catch (Exception e) {
            log.info("Exception isRollbackOnly : {}", status.isRollbackOnly());
        }
        log.info("Last isRollbackOnly {}", status.isRollbackOnly());
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    public void saveGameHistory(Game game, String content){
//        GameHistory gameHistory = GameHistory.builder()
//                .game(game)
//                .content(content)
//                .build();
//        gameHistoryRepository.save(gameHistory);
//        throw new RuntimeException();
//    }
    //    @Transactional
//    public List<Game> getTransGameAllList(){
//        return gameRepository.findAll();
//    }

}
