package com.tjetc.dao;

import com.tjetc.entity.EditArrange;

import java.util.Date;
import java.util.List;

public interface EditArrangeMapper {
	/**
	 * 删除安排
	 *
	 * @param id
	 * @return
	 */
	int delete(Long id);
	
	/**
	 * 安排编辑写稿
	 *
	 * @param record
	 * @return
	 */
	int insert(EditArrange record);
	
	EditArrange selectByPrimaryKey(Long id);
	
	/**
	 * 查询所有安排稿件
	 *
	 * @return
	 */
	List<EditArrange> selectAll();
	
	/**
	 * 更改安排
	 *
	 * @param author
	 * @param date
	 * @return
	 */
	int updateByDate(String author, Date date);

	/*
	 *
	 * */
	List<String> selectWXEdit(String username);
}