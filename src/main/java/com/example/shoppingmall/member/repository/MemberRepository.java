package com.example.shoppingmall.member.repository;

import com.example.shoppingmall.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    void update(Member member);
    void deleteByNo(Long memberNo);
    Optional<Member> findByNo(Long memberNo);
    Optional<Member> findById(String memberId);
    Optional<Member> findByEmail(String memberEmail);
    List<Member> findAll();
}
