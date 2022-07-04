package com.example.trans.service;

import com.example.trans.entity.Game;
import com.example.trans.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransService {
    private final GameRepository gameRepository;

    public List<Game> getGameAllList(){
        return gameRepository.findAll();
    }

//    @Transactional
//    public List<Game> getTransGameAllList(){
//        return gameRepository.findAll();
//    }

    public void saveGame(){
        Game game = Game.builder().name("pooney").build();
        gameRepository.save(game);
//        if(true) throw new RuntimeException();
    }

}