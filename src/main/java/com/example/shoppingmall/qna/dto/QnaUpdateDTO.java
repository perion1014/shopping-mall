package com.example.shoppingmall.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaUpdateDTO {

    private String qnaClassification;
    private String qnaTitle;
    private String qnaQuestion;
    private Long qnaNo;
}
