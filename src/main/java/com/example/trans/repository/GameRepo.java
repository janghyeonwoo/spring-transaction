package com.example.trans.repository;

import com.example.trans.entity.Game;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GameRepo {
    List<Game> findAll();
}
