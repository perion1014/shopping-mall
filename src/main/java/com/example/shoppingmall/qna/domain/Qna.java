package com.example.shoppingmall.qna.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Qna {
    private Long qnaNo;
    private Long memberNo;
    private Long itemNo;
    private String qnaClassification;
    private String qnaTitle;
    private String qnaQuestion;
    private String qnaAnswer;
    private Timestamp qnaQuestionCreatedTime;
}
