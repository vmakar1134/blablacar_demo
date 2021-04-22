package com.makar.blablacar.exception;

public class NonUniqueFieldException extends RuntimeException {

    private String fieldName;

    public String fieldValue;

    public NonUniqueFieldException(String filedName, String fieldValue) {
        this.fieldName = filedName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
