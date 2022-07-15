package com.example.trans.service;

import com.example.trans.dto.ReqGaveSave;
import com.example.trans.entity.Game;
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
public class TransService {
    private final InnerTransService innerTransService;
    private final GameRepository gameRepository;


    //게임의 전체 목록 조회
    public List<Game> getGameAllList(){
        return gameRepository.findAll();
    }

    //게임 저장
    @Transactional
    public void saveGame(ReqGaveSave reqGaveSave){
        Game game = Game.builder().name(reqGaveSave.getName()).build();
        gameRepository.save(game);
        innerTransService.saveGameHistory(game, "등록");
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
