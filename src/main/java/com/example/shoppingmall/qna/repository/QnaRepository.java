package com.example.shoppingmall.qna.repository;

import com.example.shoppingmall.qna.domain.Qna;

import java.util.List;


public interface QnaRepository {

    List<Qna> findByItemNo(Long itemNo);

    String getMemberIdByNo(Long memberNo);

    void addQna(Qna qna);
}
