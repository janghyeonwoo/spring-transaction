package com.example.trans.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.transaction.Transaction;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class TranControllerTest {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Test
    public void TEST() {
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        System.out.println("before :: " + transactionStatus.isRollbackOnly());
        if(1==1) { throw new RuntimeException(); }
        System.out.println("after :: " + transactionStatus.isRollbackOnly());

    }


}
