package com.example.newssearch.exception;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/29
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
