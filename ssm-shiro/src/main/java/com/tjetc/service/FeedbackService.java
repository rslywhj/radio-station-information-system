package com.tjetc.service;

import com.tjetc.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    /**
     * 插入反馈
     */
    void insertFeedbackService(Feedback feedback);

    /**
     * 查看所有反馈
     */
    List<Feedback> selectAll();

    /**
     * 查看需要处理的反馈
     */
    List<Feedback> selectByStatus();

    /**
     * 处理反馈
     */
    void updateStatus(Long feedback_id);
}
