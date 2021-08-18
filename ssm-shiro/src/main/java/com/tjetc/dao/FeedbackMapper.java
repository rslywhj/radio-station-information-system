package com.tjetc.dao;

import com.tjetc.entity.Feedback;
import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Long feedbackId);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Long feedbackId);

    List<Feedback> selectAll();

    int updateByPrimaryKey(Feedback record);
    /**
     * 查看需要处理的反馈
     */
    List<Feedback> selectByStatus();

    /**
     * 处理反馈
     */
    void updateStatus(Long  feedback_id);
}