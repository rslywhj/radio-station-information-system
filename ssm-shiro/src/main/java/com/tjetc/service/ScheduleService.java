package com.tjetc.service;

import com.tjetc.entity.Fdjjr;
import com.tjetc.entity.Schedule;

import java.util.List;

public interface ScheduleService {
	int deleteByPrimaryKey(Long id);
	
	int insertSchedule(Schedule record);
	
	/**
	 * 根据登录播音用户名查询安排稿名称
	 *
	 * @return
	 */
	String findByB(String date);
	
	List<Schedule> selectAll();
	
	int updateByPrimaryKey(Schedule record);
	
	List<Schedule> selectScheduleList();
	
	void addScheduleService(Schedule schedule);

	List<String> selectBYEdit(String username);

}
