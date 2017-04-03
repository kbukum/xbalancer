package org.oopdev.xbalancer.common.lang;

/**
 * Created by kamilbukum on 01/04/2017.
 * Holds Generic Data and code
 */
public class Response<T> {
    private int code;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
