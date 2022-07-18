package com.example.trans.service;

import com.example.trans.dto.ReqGaveSave;
import com.example.trans.entity.Game;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.SmartTransactionObject;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProxyTransService {
    TransService transService;
    PlatformTransactionManager platformTransactionManager;
    JpaTransactionManager jpaTransactionManager;


    public ProxyTransService(TransService transService, PlatformTransactionManager platformTransactionManager, JpaTransactionManager jpaTransactionManager) {
        this.transService = transService;
        this.platformTransactionManager = platformTransactionManager;
        this.jpaTransactionManager = jpaTransactionManager;
    }


    public List<Game> getGameAllList() {
        TransactionStatus transactionStatus = jpaTransactionManager.getTransaction(new DefaultTransactionDefinition());
        List<Game> gameList = new ArrayList<>();
        try {
            gameList =  transService.getGameAllList();
            jpaTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            if(!transactionStatus.isRollbackOnly()){
                jpaTransactionManager.rollback(transactionStatus);
            }
        }
        return gameList;
    }

    public void saveGame(ReqGaveSave reqGaveSave){
        TransactionStatus transactionStatus = jpaTransactionManager.getTransaction(new DefaultTransactionDefinition());
        ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(Objects.requireNonNull(jpaTransactionManager.getDataSource()));
        try {
            transService.saveGame(reqGaveSave);
            jpaTransactionManager.commit(transactionStatus);
        } catch (Exception e){
            assert connectionHolder != null;
            if(connectionHolder.isRollbackOnly()){
                jpaTransactionManager.rollback(transactionStatus);
            }
        }
    }
}

