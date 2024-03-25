package com.deep.night.config.response;

import com.deep.night.common.utils.LogUtils;
import lombok.Data;

@Data
public class Error {
    private ErrorType type;
    private ErrorCode code;
    private String message;
    private String errorStack;

    public Error() {
        type = ErrorType.UNKNOWN;
    }

    public Error(ErrorType type, ErrorCode code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public Error(ErrorType type, ErrorCode code, String message, Throwable e) {
        this.type = type;
        this.code = code;
        this.message = message;
        this.errorStack = LogUtils.getStackTrace(e);
    }

    public Error(Throwable e) {
        this.type = ErrorType.UNKNOWN;
        this.code = ErrorCode.UNKNOWN_SERVER_ERROR;
        this.message = e.toString();
        this.errorStack = LogUtils.getStackTrace(e);
    }

    public Error(Throwable e, String message, ErrorCode code) {
        this.type = ErrorType.UNKNOWN;
        this.message = message;
        this.code = code;
        this.message = e.toString();
        this.errorStack = LogUtils.getStackTrace(e);
    }
}