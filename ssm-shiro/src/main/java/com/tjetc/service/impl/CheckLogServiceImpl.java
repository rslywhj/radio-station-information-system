package com.tjetc.service.impl;

import com.tjetc.dao.CheckLogMapper;
import com.tjetc.entity.CheckLog;
import com.tjetc.service.CheckLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CheckLogServiceImpl implements CheckLogService {
    @Autowired
    private CheckLogMapper checkLogMapper;

    @Override
    public int insert(CheckLog record) {
        return checkLogMapper.insert(record);
    }

    @Override
    public CheckLog findOne(Long id) {
        return checkLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean selectByUsername(String username) {
        List<Date> dates = checkLogMapper.selectByUsername(username);
        for (Date date : dates) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(date);
            String format = sdf.format(new Date());
            if(s.equals(format)){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<CheckLog> selectPersonCheck(String username) {
        List<CheckLog> checkLogs = checkLogMapper.selectPersonCheck(username);
        return checkLogs;
    }

    @Override
    public List<CheckLog> selectGroup(String username) {
        List<CheckLog> checkLogs = checkLogMapper.selectGroup(username);
        return checkLogs;
    }

    @Override
    public List<CheckLog> selectAllGroup(String check_datetime) {
        List<CheckLog> checkLogs = checkLogMapper.selectAllGroup(check_datetime);
        return checkLogs;
    }
}
