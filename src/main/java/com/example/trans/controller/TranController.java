package com.example.trans.controller;

import com.example.trans.entity.Game;
import com.example.trans.service.ProxyTransService;
import com.example.trans.service.TransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("trans")
@RequiredArgsConstructor
@RestController
public class TranController {
//    private final ProxyTransService transService;
    private final TransService transService;

    @GetMapping("/game/list")
    public String getTrans(){
      List<Game> gameList = transService.getGameAllList();
      return "test";
    }

    @GetMapping("/game/save")
    public String saveGame(){
        transService.saveGame();
        return "success";
    }

    @GetMapping("/game/jpa")
    public String getJpa(){
        transService.jpaMyBatis();
        return "jpa";

    }
}
