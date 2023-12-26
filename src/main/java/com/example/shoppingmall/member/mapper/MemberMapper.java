package com.example.shoppingmall.member.mapper;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
import com.example.shoppingmall.member.form.MemberSearchForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface MemberMapper {
    void save(Member member);
    void update(Member member);
    void deleteByNo(Long memberNo);
    Member findByNo(Long memberNo);
    Member findById(String memberId);
    Member findByEmail(String memberEmail);
    List<Member> findAllByPaging(Map<String, Integer> pagingSettings);
    List<Member> findAllByKeyword(MemberSearchForm memberSearchForm);
    Long countAll();
    Long countAllByKeyword(MemberSearchForm memberSearchForm);



}
