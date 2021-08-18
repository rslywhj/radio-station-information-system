package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Feedback;
import com.tjetc.service.FeedbackService;
import com.tjetc.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private MemberService service;

    /**
     * 提交反馈
     * @param content
     * @return
     */
    @RequestMapping("insertFeedback")
    @ResponseBody
    public JsonResult insertFeedback(@RequestParam("content") String content) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        String memberName = service.findByUsername(username).getMemberName();
        Date date = new Date();
        Feedback feedback = new Feedback();
        feedback.setFeedbackDate(date);
        feedback.setFeedbackUsername(memberName);
        feedback.setContent(content);
        feedback.setStatus("未处理");
        feedbackService.insertFeedbackService(feedback);
        return new JsonResult(0, "反馈成功", null);
    }

    /**
     * 查看全部反馈
     * @return
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public JsonResult selectAll(){
        List<Feedback> feedbacks = feedbackService.selectAll();
        return new JsonResult(0,"全部反馈",feedbacks);
    }
    /**
     * 查看需要处理的反馈
     */
    @RequestMapping("selectByStatus")
    @ResponseBody
    public  JsonResult selectByStatus(){
        List<Feedback> feedbacks = feedbackService.selectByStatus();
        return new JsonResult(0,"需要处理的反馈",feedbacks);

    }

    /**
     * 处理反馈
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public JsonResult updateStatus(@RequestParam("id")Long id){
        feedbackService.updateStatus(id);
        return new JsonResult(0,"",null);
    }
}
