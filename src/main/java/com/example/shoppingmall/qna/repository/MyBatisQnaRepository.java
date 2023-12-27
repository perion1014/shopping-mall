package com.example.shoppingmall.qna.repository;

import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.qna.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Qna> findAllByPaging(Map<String, Integer> pagingSettings){
       return qnaMapper.findAllByPaging(pagingSettings);
    }

    @Override
    public Long countAll() {
        return qnaMapper.countAll();
    }

    @Override
    public Qna findByQnaNo(Long qnaNo) {
        return qnaMapper.findByQnaNo(qnaNo);
    }

}
