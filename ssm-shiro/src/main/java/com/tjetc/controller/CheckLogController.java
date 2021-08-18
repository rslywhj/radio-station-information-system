package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.CheckLog;
import com.tjetc.entity.Member;
import com.tjetc.service.CheckLogService;
import com.tjetc.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/check")
public class CheckLogController {
    @Autowired
    private CheckLogService checkLogService;
    @Autowired
    private MemberService memberService;

    @RequestMapping("/in")
    @ResponseBody
    public JsonResult insert() {
        String name = (String) SecurityUtils.getSubject().getPrincipal();
        String username = memberService.findByUsername(name).getMemberName();
        Boolean aBoolean = checkLogService.selectByUsername(username);
        if (aBoolean) {
            CheckLog checkLog = new CheckLog();
            checkLog.setCheckDatetime(LocalDateTime.now());
            checkLog.setUsername(username);
            int result = checkLogService.insert(checkLog);
            if (result != 0) {
                return new JsonResult(0, "打卡成功", null);
            } else {
                return new JsonResult(1, "打卡失败", null);
            }
        }else {
            return new JsonResult(1,"今天已打卡，请勿重复打卡",null);
        }
    }

    @RequestMapping("selectPersonCheck")
    @ResponseBody
    public JsonResult selectPersonCheck(){
        String name = (String) SecurityUtils.getSubject().getPrincipal();
        String username = memberService.findByUsername(name).getMemberName();
        List<CheckLog> checkLogs = checkLogService.selectPersonCheck(username);
        Collections.reverse(checkLogs);
        return new JsonResult(0,"打卡记录",checkLogs);
    }

    @RequestMapping("selectGroup")
    @ResponseBody
    public JsonResult selectGroup(){
        String name = (String) SecurityUtils.getSubject().getPrincipal();
        String username = memberService.findByUsername(name).getMemberName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        List<CheckLog> checkLogs = checkLogService.selectGroup(username);
        List<CheckLog> checkLogs1=new ArrayList<>();
        for (CheckLog checkLog : checkLogs) {
            LocalDateTime checkDatetime = checkLog.getCheckDatetime();
            Date date = Date.from( checkDatetime.atZone( ZoneId.systemDefault()).toInstant());
            String format1 = sdf.format(date);
            if(format1.equals(format)){
                checkLogs1.add(checkLog);
            }
        }
        return new JsonResult(0,"小组打卡记录",checkLogs1);
    }

    @RequestMapping("selectAllGroup")
    @ResponseBody
    public JsonResult selectAllGroup(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        List<CheckLog> checkLogs = checkLogService.selectAllGroup(format);
        return new JsonResult(0,"全台打卡情况",checkLogs);
    }
}
