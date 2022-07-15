package com.example.trans.service;

import com.example.trans.dto.ReqGaveSave;
import com.example.trans.entity.Game;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProxyTransService {
    TransService transService;
    PlatformTransactionManager platformTransactionManager;


    public ProxyTransService(TransService transService, PlatformTransactionManager platformTransactionManager) {
        this.transService = transService;
        this.platformTransactionManager = platformTransactionManager;
    }


    public List<Game> getGameAllList() {
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        List<Game> gameList = new ArrayList<>();
        try {
            gameList =  transService.getGameAllList();
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionStatus.setRollbackOnly();
        }
        return gameList;
    }

    public void saveGame(ReqGaveSave reqGaveSave){
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            transService.saveGame(reqGaveSave);
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e){
            platformTransactionManager.rollback(transactionStatus);
        }
    }
}

