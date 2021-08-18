package com.tjetc.service;

import com.tjetc.entity.Fdjjr;

import java.util.List;

public interface FdjjrService {
    int deleteByPrimaryKey(Long id);

    int insertFdjjr(Fdjjr record);

    Fdjjr selectByPrimaryKey(Long id);

    List<Fdjjr> selectAll();

    int updateByPrimaryKey(Fdjjr record);
}
