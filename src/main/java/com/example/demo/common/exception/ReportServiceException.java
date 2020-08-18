package com.example.demo.common.exception;

import lombok.Data;

/**
 * @description:
 * @author: ch
 * @version: 1.0
 * @createDate: 2020/6/24  10:49
 */
@Data
public class ReportServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorMsg;


    public ReportServiceException(String errorCode, String errorMsg) {
        super("[" + errorCode + "]" + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ReportServiceException(ReportSystemErrorCode errorEnum) {
        super("[" + errorEnum.getCode() + "]" + errorEnum.getDesc());
        this.errorCode = errorEnum.getCode();
        this.errorMsg = errorEnum.getDesc();
    }
}
