package com.example.shoppingmall.member.repository;

import com.example.shoppingmall.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    void update(Member member);
    void deleteById(String memberId);
    Optional<Member> findById(String memberId);
    List<Member> findAll();
}
