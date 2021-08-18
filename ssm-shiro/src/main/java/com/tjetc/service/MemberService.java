package com.tjetc.service;

import com.tjetc.entity.Member;
import com.tjetc.entity.vo.MemberPermissionVo;


import java.util.List;

public interface MemberService {
	Member findByUsername(String username);
	
	MemberPermissionVo findVoByUsername(String username);
	
	List<Member> findAll();



	int updateByPrimaryKey(Member member);
	int deleteByPrimaryKey(Long memberId);
	int insert(Member member);List<String> findPermissionNamesByUsername(String username);

	/**
	 * 选择编辑
	 */
	List<String> selectEdit();

	/**
	 * 选择播音
	 */
	List<String> selectBroadcast();





}
