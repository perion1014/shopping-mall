package com.example.shoppingmall.member.exception;

public class DuplicatedHpException extends RuntimeException{
    public DuplicatedHpException() {
    }

    public DuplicatedHpException(String message) {
        super(message);
    }

}
