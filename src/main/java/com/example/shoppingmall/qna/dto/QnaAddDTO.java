package com.example.shoppingmall.qna.dto;

import com.example.shoppingmall.qna.domain.Qna;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaAddDTO {
    private String qnaClassification;
    private String qnaTitle;
    private String qnaQuestion;

    public static Qna QnaAddDTOToQna(Long memberNo, Long itemNo, QnaAddDTO qnaAddDTO){
          Qna qna = new Qna();

          qna.setMemberNo(memberNo);
          qna.setItemNo(itemNo);
          qna.setQnaClassification(qnaAddDTO.getQnaClassification());
          qna.setQnaTitle(qnaAddDTO.getQnaTitle());
          qna.setQnaQuestion(qnaAddDTO.getQnaQuestion());
        return qna;
    }
}
