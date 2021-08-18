package com.tjetc.service.impl;

import com.tjetc.dao.MemberMapper;
import com.tjetc.dao.MemberMapper;
import com.tjetc.dao.PermissionMapper;
import com.tjetc.entity.Member;
import com.tjetc.entity.Member;
import com.tjetc.entity.Permission;
import com.tjetc.entity.vo.MemberPermissionVo;
import com.tjetc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public Member findByUsername(String username) {
		return memberMapper.selectByUsername(username);
	}
	
	@Override
	public MemberPermissionVo findVoByUsername(String username) {
		return memberMapper.selectVoByUsername(username);
	}
	
	@Override
	public List<Member> findAll() {
		return memberMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Member member) {
		return memberMapper.updateByPrimaryKey(member);
	}

	@Override
	public int deleteByPrimaryKey(Long memberId) {
		return memberMapper.deleteByPrimaryKey(memberId);
	}

	@Override
	public int insert(Member member) {
		return memberMapper.insert(member);
	}

	@Override
	public List<String> findPermissionNamesByUsername(String username) {
		//根据用户名查询用户的权限信息，如果查询的用户 是root，那么单独查询所有的权限信息  非root用户 查询权限信息 多表联查
		MemberPermissionVo memberPermissionVo = findVoByUsername(username);
		//权限名称列表
		List<String> permissionNames = memberPermissionVo.getPermissionNames();
		if (memberPermissionVo.getMemberRoot()) { //判断username是root用户
			//如果是root用户查询所有的权限信息
			List<Permission> permissions = permissionMapper.selectAll();
			for (Permission permission : permissions) {
				permissionNames.add(permission.getPermissionName());
			}
		}
		return permissionNames;
	}

	@Override
	public List<String> selectEdit() {
		List<String> list = memberMapper.selectEdit();
		return list;
	}

	@Override
	public List<String> selectBroadcast() {
		List<String> list = memberMapper.selectBroadcast();
		return list;
	}

}