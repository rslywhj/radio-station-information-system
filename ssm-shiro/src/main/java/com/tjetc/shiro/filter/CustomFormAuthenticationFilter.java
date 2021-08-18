package com.tjetc.shiro.filter;


import com.alibaba.fastjson.JSONObject;
import com.tjetc.common.JsonResult;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 重写方法目的：当用户未登录或者登录过期后，需要登录的访问的url，执行这个方法，返回json，防止后端跳转登录页面
     * 这个方法的执行时机：当用户未登录或者登录过期后，访问需要登录的url，就会执行下面的方法
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取HttpSerlvetResponse对象
        HttpServletResponse resp = (HttpServletResponse) response;
        //设置响应的内容类型
        resp.setContentType("application/json;charset=UTF-8");
        //获取PrintWriter对象
        PrintWriter writer = resp.getWriter();
        //获取JsonResult对象（未登录或者登录过期信息）
        JsonResult<Object> jsonResult = new JsonResult(-1, "未登录或登录过期", null);
        //JsonResult转换成json字符串
        String json = JSONObject.toJSONString(jsonResult);
        //PrintWriter输出json字符串
        writer.write(json);
        //刷新
        writer.flush();
        //关闭流
        writer.close();
        //return false 防止后端跳转登录页面
        return false;
    }
}
