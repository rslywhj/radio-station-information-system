package com.tjetc.service.impl;

import com.tjetc.dao.PermissionMapper;
import com.tjetc.entity.Permission;
import com.tjetc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectAll();
    }
}
