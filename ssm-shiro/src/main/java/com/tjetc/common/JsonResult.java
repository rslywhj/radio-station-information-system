package com.tjetc.common;

public class JsonResult<T> {
    //状态 0 成功  1失败 -1登录过期或者未登录
    private int state;
    //消息
    private String msg;
    //数据
    private T data;

    public JsonResult() {
    }

    public JsonResult(int state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
