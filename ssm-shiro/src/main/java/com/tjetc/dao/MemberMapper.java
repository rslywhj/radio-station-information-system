package com.tjetc.dao;

import com.tjetc.entity.Member;
import com.tjetc.entity.Role;
import com.tjetc.entity.vo.MemberPermissionVo;

import java.util.List;

public interface MemberMapper {
    Member selectByUsername(String username);
    
    MemberPermissionVo selectVoByUsername(String username);
    
    List<Member> selectAll();
    int updateByPrimaryKey(Member member);
    int deleteByPrimaryKey(Long memberId);
    int insert(Member member);

    /**
     * 选择编辑
     */
    List<String> selectEdit();

    /**
     * 选择播音
     */
    List<String> selectBroadcast();



}