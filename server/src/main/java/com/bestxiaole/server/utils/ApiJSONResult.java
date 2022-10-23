package com.bestxiaole.server.utils;

/**
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */
public class ApiJSONResult {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ApiJSONResult build(Integer status, String msg, Object data) {
        return new ApiJSONResult(status, msg, data);
    }

    public static ApiJSONResult ok(Object data) {
        return new ApiJSONResult(data);
    }

    public static ApiJSONResult ok() {
        return new ApiJSONResult(null);
    }
    
    public static ApiJSONResult errorMsg(String msg) {
        return new ApiJSONResult(500, msg, null);
    }
    
    public static ApiJSONResult errorMap(Object data) {
        return new ApiJSONResult(501, "error", data);
    }
    
    public static ApiJSONResult errorTokenMsg(String msg) {
        return new ApiJSONResult(502, msg, null);
    }
    
    public static ApiJSONResult errorException(String msg) {
        return new ApiJSONResult(555, msg, null);
    }

    public ApiJSONResult() {

    }

    public ApiJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}