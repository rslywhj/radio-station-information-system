package com.tjetc.dao;

import com.tjetc.entity.Schedule;

import java.util.List;

public interface ScheduleMapper {
	int deleteByPrimaryKey(Long id);
	
	int insert(Schedule record);
	
	/**
	 * 根据登录播音用户名和日期查询安排稿名称
	 *
	 * @return
	 */
	String selectByB(String date);
	
	List<Schedule> selectAll();
	
	int updateByPrimaryKey(Schedule record);
	
	List<Schedule> selectScheduleList();
	List<String> selectBYEdit(String username);

}