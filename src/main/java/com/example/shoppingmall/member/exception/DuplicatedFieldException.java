package com.example.shoppingmall.member.exception;

import lombok.Getter;

@Getter
public class DuplicatedFieldException extends RuntimeException{

    private final String fieldName;

    public DuplicatedFieldException(String fieldName,String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
