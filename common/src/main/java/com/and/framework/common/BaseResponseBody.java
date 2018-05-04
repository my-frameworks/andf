package com.and.framework.common;

public class BaseResponseBody<T> {
    private int err;//0:业务处理成功,!0 :业务处理错误
    private String msg;
    private T data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
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
