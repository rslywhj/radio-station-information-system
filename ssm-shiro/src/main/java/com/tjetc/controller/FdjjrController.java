package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Schedule;
import com.tjetc.service.ArticleService;
import com.tjetc.service.ScheduleService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("jjrgl/schedule/")
public class FdjjrController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ArticleService articleService;
    @RequestMapping("add")
    @ResponseBody
    public JsonResult insert(@Param("date") String date,
                             @Param("edit") String edit,
                             @Param("broadcast") String broadcast,
                             @Param("group") String group) {
        Schedule schedule = new Schedule();
        schedule.setEdit(edit);
        schedule.setBroadcast(broadcast);
        schedule.setGroup(group);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = simpleDateFormat.parse(date);
            schedule.setDate(date1);
            scheduleService.insertSchedule(schedule);
            return new JsonResult(0, "添加成功", null);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JsonResult(1, "添加失败", null);
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonResult update(@Param("id") Long id,
                             @Param("date") String date,
                             @Param("edit") String edit,
                             @Param("broadcast") String broadcast,
                             @Param("group") String group) {
        Schedule schedule = new Schedule();
        schedule.setId(id);
        schedule.setEdit(edit);
        schedule.setBroadcast(broadcast);
        schedule.setGroup(group);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = simpleDateFormat.parse(date);
            schedule.setDate(date1);
            scheduleService.updateByPrimaryKey(schedule);
            return new JsonResult(0, "修改成功", null);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JsonResult(1, "修改失败", null);
        }
    }

    /**
     * 栏目总编安排定稿
     * @param id
     * @param date
     * @param edit
     * @param broadcast
     * @param group
     * @param article
     * @return
     */
    @RequestMapping("update2")
    @ResponseBody
    public JsonResult update2(@Param("id") Long id,
                             @Param("date") String date,
                             @Param("edit") String edit,
                             @Param("broadcast") String broadcast,
                             @Param("group") String group,
                              @Param("article")String article) {
        Schedule schedule = new Schedule();
        schedule.setId(id);
        schedule.setEdit(edit);
        schedule.setBroadcast(broadcast);
        schedule.setGroup(group);
        schedule.setArticle(article);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = simpleDateFormat.parse(date);
            schedule.setDate(date1);
            scheduleService.updateByPrimaryKey(schedule);
            articleService.updateStatus(article);
            return new JsonResult(0, "修改成功", null);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JsonResult(1, "修改失败", null);
        }
    }

    @RequestMapping("/fdjjrList")
    @ResponseBody
    public JsonResult fdjjrList() {
        /*List<Map<String, Object>> resultList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //保存日期集合
        List<String> dateList = new ArrayList<String>();*/
        List<Schedule> list = scheduleService.selectAll();
        for (int i = 0; i < list.size(); i++) {
            Schedule schedule = list.get(i);
            Date date = schedule.getDate();
            /*System.out.println("date = " + date);*/
        }
        return new JsonResult(0, "", list);
    }
    @RequestMapping("delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id")Long id){
        scheduleService.deleteByPrimaryKey(id);
        return true;
    }

}
