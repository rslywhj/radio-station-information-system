package com.tjetc.service.impl;

import com.tjetc.dao.FeedbackMapper;
import com.tjetc.entity.Feedback;
import com.tjetc.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void insertFeedbackService(Feedback feedback) {
        feedbackMapper.insert(feedback);
    }

    @Override
    public List<Feedback> selectAll() {
        List<Feedback> feedbacks = feedbackMapper.selectAll();
        return feedbacks;
    }

    @Override
    public List<Feedback> selectByStatus() {
        List<Feedback> feedbacks = feedbackMapper.selectByStatus();
        return feedbacks;
    }

    @Override
    public void updateStatus(Long feedback_id) {
        feedbackMapper.updateStatus(feedback_id);
    }
}
