package com.deep.night.demo.config.response;

import lombok.Data;

/**
 * Created by sai on 2016. 3. 18..
 */
@Data
public class Result<T> {
    private boolean success = true;
    private String version = "v1";
    private Error error;
    private T data;
    long startTime = System.currentTimeMillis();

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    public Result(Throwable e, ErrorCode errorCode, String message) {
        this.error = new Error(ErrorType.UNKNOWN, errorCode, message, e);
        this.success = false;
    }

    public Result(Throwable e) {
        this.error = new Error(e);
        this.success = false;
    }

    public Result(ErrorType type, ErrorCode errorCode, String message) {
        this.error = new Error(type, errorCode, message);
        this.success = false;
    }

    public Result(ErrorType type, ErrorCode errorCode, String message, T data) {
        this.error = new Error(type, errorCode, message);
        this.data = data;
        this.success = true;
    }



}


