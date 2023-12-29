package com.example.shoppingmall.qna.mapper;

import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.dto.QnaUpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface QnaMapper {
    List<Qna> findByItemNo(Long itemNo);

    String getMemberIdByNo(Long memberNo);

    Long getmemberNoById(String memberId);

    void addQna(Qna qna);

    List<Qna> findAllByPaging(Map<String, Integer> pagingSettings);

    Long countAll();

    Qna findByQnaNo(Long qnaNo);

    void replyQna(Qna qna);

    void deleteAnswer(Long qnaNo);

    List<Qna> findQnaByPaging(@Param("startPage")int startPage,
                         @Param("pagePerMember") int pagePerMember,
                         @Param("itemNo") Long itemNo);

    Long countQnaByitemNo(Long itemNo);

    List<Qna> findMQnaByPaging(@Param("startPage")int startPage,
                              @Param("pagePerMember") int pagePerMember,
                              @Param("memberNo") Long memberNo);


    Long countMemberQna(Long memberNo);

    void updateQna(QnaUpdateDTO qnaUpdateDTO);

    void deleteQna(Long qnaNo);
}
