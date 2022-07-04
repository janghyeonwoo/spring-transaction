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
    private final ProxyTransService transService;

    @GetMapping("test")
    public String getTrans(){
//      List<Game> gameList = trasService.getTransGameAllList();
      return "test";
    }

    @GetMapping("/save/game")
    public String saveGame(){
        transService.saveGame();
        return "success";
    }
}
