package com.example.trans.controller;

import com.example.trans.dto.ReqGaveSave;
import com.example.trans.entity.Game;
import com.example.trans.service.ProxyTransService;
import com.example.trans.service.TransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("/game/save")
    public String saveGame(@RequestBody ReqGaveSave reqGaveSave) throws IOException {
        transService.saveGame(reqGaveSave);
        return "success";
    }

//    @GetMapping("/game/jpa")
//    public String getJpa(){
//        transService.jpaMyBatis();
//        return "jpa";
//
//    }
}
