package com.tjetc.service.impl;

import com.tjetc.dao.FdjjrMapper;
import com.tjetc.entity.Fdjjr;
import com.tjetc.service.FdjjrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FdjjrServiceImpl implements FdjjrService {
    @Autowired
    private FdjjrMapper fdjjrMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return fdjjrMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertFdjjr(Fdjjr record) {
        return fdjjrMapper.insert(record);
    }


    @Override
    public Fdjjr selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Fdjjr> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Fdjjr record) {
        return 0;
    }
}
