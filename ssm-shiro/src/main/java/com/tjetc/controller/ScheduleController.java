package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Schedule;
import com.tjetc.service.ArticleService;
import com.tjetc.service.ScheduleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tutor")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult selectAll(){
        List<Schedule> schedules = scheduleService.selectAll();
        return new JsonResult(0,"全部信息",schedules);
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonResult addSchedule(@RequestParam("date") Date dateTime,
                                  @RequestParam("peple") String peple,
                                  @RequestParam("title") String title){
        Schedule schedule = new Schedule();
        schedule.setDate(dateTime);
        scheduleService.addScheduleService(schedule);
        return new JsonResult(0,"添加成功",null);
    }
    @RequestMapping("/selectBYEdit")
    @ResponseBody
    public JsonResult selectBYEdit(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<String> list2 = scheduleService.selectBYEdit(username);
        return new JsonResult(0,"个人安排",list2);
    }
}
