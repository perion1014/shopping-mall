package com.example.shoppingmall.qna.repository;

import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.qna.dto.QnaDTO;

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
}
