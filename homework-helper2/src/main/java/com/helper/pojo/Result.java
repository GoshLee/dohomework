package com.helper.pojo;

public class Result {
    private Integer status;
    private Object data;
    private String msg;

    public static Result build(Integer status,Object data,String msg){
        return new Result(status,data,msg);
    }

    public Result(){

    }

    public Result(Integer status, Object data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
