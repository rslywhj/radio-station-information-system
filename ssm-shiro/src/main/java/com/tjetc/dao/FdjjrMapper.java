package com.tjetc.dao;

import com.tjetc.entity.Fdjjr;
import java.util.List;

public interface FdjjrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fdjjr record);

    Fdjjr selectByPrimaryKey(Long id);

    List<Fdjjr> selectAll();

    int updateByPrimaryKey(Fdjjr record);
}