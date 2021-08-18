package com.tjetc.dao;

import com.tjetc.entity.MemberRole;
import java.util.List;

public interface MemberRoleMapper {
    int insert(MemberRole record);

    List<MemberRole> selectAll();
}