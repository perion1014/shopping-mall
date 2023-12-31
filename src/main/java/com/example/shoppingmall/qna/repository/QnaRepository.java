package com.example.shoppingmall.qna.repository;

import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.dto.QnaUpdateDTO;

import java.util.List;
import java.util.Map;


public interface QnaRepository {

    List<Qna> findByItemNo(Long itemNo);

    String getMemberIdByNo(Long memberNo);

    void addQna(Qna qna);

    List<Qna> findAllByPaging(Map<String, Integer> pagingSettings);

    Long countAll();

    Qna findByQnaNo(Long qnaNo);

    void replyQna(Qna qna);

    void deleteAnswer(Long qnaNo);

    List<Qna> findQnaByPaging(int startPage, int pagePerQna, Long itemNo);

    Long countQnaByitemNo(Long itemNo);

    List<Qna> findMQnaByPaging(int startPage, int pagePerQna, Long memberNo);

    Long countMemberQna(Long memberNo);

    void updateQna(QnaUpdateDTO qnaUpdateDTO);

    void deleteQna(Long qnaNo);

    List<Qna> findQnaAnswered(Map<String, Integer> pagingSettings);

    Long countQnaAnswered();

    List<Qna> findUnansweredQna(Map<String, Integer> pagingSettings);

    Long countUnansweredQna();

    String getItemName(Long itemNo);
}
