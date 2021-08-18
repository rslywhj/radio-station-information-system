package com.tjetc.service.impl;

import com.tjetc.dao.ScheduleMapper;
import com.tjetc.entity.Schedule;
import com.tjetc.service.ScheduleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return scheduleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSchedule(Schedule record) {
        return scheduleMapper.insert(record);
    }
    
    @Override
    public String findByB(String date) {
        return scheduleMapper.selectByB(date);
    }
    
    @Override
    public List<Schedule> selectAll() {
        return scheduleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Schedule record) {
        return scheduleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Schedule> selectScheduleList() {
        return scheduleMapper.selectScheduleList();
    }
    @Override
    public void addScheduleService(Schedule schedule) {
        scheduleMapper.insert(schedule);
    }
    @Override
    public List<String> selectBYEdit(String username) {
        List<String> list2 = scheduleMapper.selectBYEdit(username);
        return list2;
    }
}
