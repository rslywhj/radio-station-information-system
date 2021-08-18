package com.tjetc.controller;

import com.tjetc.entity.Course;
import com.tjetc.service.CourseService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //@RequiresPermissions() 访问url对应的方法 需要权限
    //@RequiresRoles()  访问url对应的方法需要的角色
    //logical表示逻辑的含义，枚举值有2个 Logical.OR 表示权限或者关系   Logical.AND 表示权限并且关系
    @RequiresPermissions(value = {"admin:teach","admin:exam"},logical = Logical.OR)
    @RequestMapping("/{id}")
    @ResponseBody
    public Course detail(@PathVariable("id") Long id) {
        return courseService.findById(id);
    }
}
