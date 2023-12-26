package com.example.shoppingmall.member.repository;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
import com.example.shoppingmall.member.form.MemberSearchForm;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    void update(Member member);
    void deleteByNo(Long memberNo);
    Optional<Member> findByNo(Long memberNo);
    Optional<Member> findById(String memberId);
    Optional<Member> findByEmail(String memberEmail);
    List<Member> findByNoContaining(Long memberNo);
    List<Member> findByKeyword(Map<String, String> searchingKeyword);
    List<Member> findAllByPaging(Map<String, Integer> pagingSettings);
    Long countAll();
    Long countAllByKeyword(Map<String, String> searchingKeyword);

    //kch
    Long countAllByKeyword2(MemberSearchForm memberSearchForm);

    List<Member> findByKeyword2(MemberSearchForm memberSearchForm);
}
