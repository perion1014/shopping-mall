package com.example.shoppingmall.qna.dto;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
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
public class QnaDTO {
    // qnaToQnaDTO
    private String memberId;
    private String qnaClassification;
    private String qnaTitle;
    private String qnaQuestion;
    private String qnaAnswer;
    private Timestamp qnaQuestionCreatedTime;

    // 나머지
    private Long qnaNo;
    private Long memberNo;
    private Long itemNo;

    private String itemName;

    public static QnaDTO qnaToQnaDTO(Qna qna, String memberId) {
        QnaDTO qnaDTO = new QnaDTO();

        qnaDTO.setMemberId(memberId);

        qnaDTO.setQnaClassification(qna.getQnaClassification());
        qnaDTO.setQnaTitle(qna.getQnaTitle());
        qnaDTO.setQnaQuestion(qna.getQnaQuestion());
        qnaDTO.setQnaAnswer(qna.getQnaAnswer());
        qnaDTO.setQnaQuestionCreatedTime(qna.getQnaQuestionCreatedTime());

        qnaDTO.setQnaNo(qna.getQnaNo());
        qnaDTO.setMemberNo(qna.getMemberNo());
        qnaDTO.setItemNo(qna.getItemNo());

        return qnaDTO;
    }

    public static QnaDTO fromEntity(Qna qna, String memberId){

        QnaDTO qnaDTO = new QnaDTO();

        qnaDTO.setQnaNo(qna.getQnaNo());
        qnaDTO.setMemberNo(qna.getMemberNo());
        qnaDTO.setItemNo(qna.getItemNo());
        qnaDTO.setMemberId(memberId);
        qnaDTO.setQnaClassification(qna.getQnaClassification());
        qnaDTO.setQnaQuestion(qna.getQnaQuestion());
        qnaDTO.setQnaTitle(qna.getQnaTitle());
        qnaDTO.setQnaAnswer(qna.getQnaAnswer());
        qnaDTO.setQnaQuestionCreatedTime(qna.getQnaQuestionCreatedTime());

        return qnaDTO;
    }
}
