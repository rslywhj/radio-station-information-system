package com.tjetc.service;


import com.tjetc.entity.CheckLog;

import java.util.List;

public interface CheckLogService {
    int insert(CheckLog record);
    CheckLog findOne(Long id);

    /**
     * 查询今天是否打过卡
     */
    Boolean selectByUsername(String username);

    /**
     * 查看自己的打卡记录
     */
    List<CheckLog> selectPersonCheck(String username);

    /**
     * 组长查看自己组今天的打卡情况
     */
    List<CheckLog> selectGroup(String username);

    /**
     * 台长查看全台今天打卡情况
     */
    List<CheckLog> selectAllGroup(String check_datetime);
}
