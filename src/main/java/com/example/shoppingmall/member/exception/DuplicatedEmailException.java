package com.example.shoppingmall.member.exception;

public class DuplicatedEmailException extends RuntimeException{
    public DuplicatedEmailException() {
    }

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
