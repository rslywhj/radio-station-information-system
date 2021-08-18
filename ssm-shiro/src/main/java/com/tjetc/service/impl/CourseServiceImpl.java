package com.tjetc.service.impl;

import com.tjetc.dao.CourseMapper;
import com.tjetc.entity.Course;
import com.tjetc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course findById(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }
}
