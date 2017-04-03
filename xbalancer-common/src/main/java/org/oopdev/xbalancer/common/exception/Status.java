package org.oopdev.xbalancer.common.exception;

/**
 * Created by kamilbukum on 01/04/2017.
 */
public class Status {
    private int code;
    private String message;

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
