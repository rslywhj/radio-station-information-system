package com.tjetc.service.impl;

import com.tjetc.dao.EditArrangeMapper;
import com.tjetc.entity.EditArrange;
import com.tjetc.service.EditArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EditArrangeServiceImpl implements EditArrangeService {
	@Autowired
	private EditArrangeMapper editArrangeMapper;
	
	@Override
	public int delete(Long id) {
		return editArrangeMapper.delete(id);
	}
	
	@Override
	public int add(EditArrange record) {
		return editArrangeMapper.insert(record);
	}
	
	@Override
	public EditArrange findByPrimaryKey(Long id) {
		return null;
	}
	
	@Override
	public List<EditArrange> findAll() {
		return editArrangeMapper.selectAll();
	}
	
	@Override
	public int updateByDate(String author,Date date) {
		return editArrangeMapper.updateByDate(author,date);
	}


	@Override
	public List<String> selectWXEdit(String username) {
		List<String> list1 = editArrangeMapper.selectWXEdit(username);
		return list1;
	}
}
