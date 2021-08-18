package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.EditArrange;
import com.tjetc.service.EditArrangeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/editArrange")
@ResponseBody
public class EditArrangeController {
    @Autowired
    private EditArrangeService editArrangeService;

    /**
     * 安排编辑写稿
     *
     * @param date
     * @param memberName
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonResult insert(@RequestParam("arrangeDate") String date,
                             @RequestParam("author") String memberName) {
        EditArrange editArrange = new EditArrange();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = simpleDateFormat.parse(date);
            editArrange.setArrangeDate(date1);
            editArrange.setAuthor(memberName);
            int add = editArrangeService.add(editArrange);
            return new JsonResult(0, "", add);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JsonResult(1,"安排失败",null);
        }
    }

    /**
     * 查询所有安排结果
     *
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public JsonResult select() {
        List<EditArrange> editArranges = editArrangeService.findAll();
        return new JsonResult(0, "", editArranges);
    }

    /**
     * 更改编辑安排
     *
     * @param date
     * @param memberName
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestParam("author") String memberName,
                             @RequestParam("date") Date date) {
        int update = editArrangeService.updateByDate(memberName, date);
        return new JsonResult(0, "", update);
    }

    /**
     * 删除安排
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable("id")Long id) {
        int delete = editArrangeService.delete(id);
        return new JsonResult(0, "", delete);
    }

    @RequestMapping("/selectWXEdit")
    @ResponseBody
    public JsonResult selectWXEdit(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<String> list1 = editArrangeService.selectWXEdit(username);
        return new JsonResult(0,"个人安排",list1);
    }
}
