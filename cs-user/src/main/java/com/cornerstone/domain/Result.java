package com.cornerstone.domain;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * @author: liyl
 * @date: 2018/2/7 上午11:06
 * @since 1.0.0
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -6792292881295765964L;

    private Integer status;

    private String message;

    private Object result;

    public Result(Integer status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return jsonObject.toString();
    }
}
