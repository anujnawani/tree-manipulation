package com.hingehealth.demo.enums;

public enum ErrorCode {
    PARENT_NOT_FOUND("TR_100"),
    ROOT_ALREADY_EXIST("TR_101"),
    INTERNAL_ERROR("TR_102"),
    BAD_REQUEST("TR_103");

    private String value;
    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
