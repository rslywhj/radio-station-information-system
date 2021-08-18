package com.tjetc.service;

import com.tjetc.entity.EditArrange;

import java.util.Date;
import java.util.List;

public interface EditArrangeService {
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
	int add(EditArrange record);
	
	EditArrange findByPrimaryKey(Long id);
	
	/**
	 * 显示全部安排
	 *
	 * @return
	 */
	List<EditArrange> findAll();
	
	/**
	 * 更改安排
	 *
	 * @param author
	 * @param date
	 * @return
	 */
	int updateByDate(String author,Date date);

	/**
	 * 查看个人安排
	 * @param username
	 * @return
	 */
	List<String> selectWXEdit(String username);

}
