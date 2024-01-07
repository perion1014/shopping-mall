package com.example.shoppingmall.common.exceptionhandler;

import com.example.shoppingmall.member.exception.DuplicatedFieldException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DuplicatedFieldException.class)
    public String handleMemberAddException(DuplicatedFieldException e, Model model) {
        model.addAttribute("fieldName", e.getFieldName());
        model.addAttribute("errorMessage", e.getMessage());
        return "/members/add-member";
    }
}
