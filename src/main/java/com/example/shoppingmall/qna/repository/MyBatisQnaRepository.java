package com.example.shoppingmall.qna.repository;

import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.qna.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisQnaRepository implements QnaRepository {

    private final QnaMapper qnaMapper;

    @Override
    public List<Qna> findByItemNo(Long itemNo) {
        return qnaMapper.findByItemNo(itemNo);
    }

    @Override
    public String getMemberIdByNo(Long memberNo) {
        return qnaMapper.getMemberIdByNo(memberNo);
    }

    @Override
    public void addQna(Qna qna) { qnaMapper.addQna(qna); }

}
