package com.example.shoppingmall.member.repository;

import com.example.shoppingmall.member.domain.Member;

import java.util.List;
import java.util.Optional;

public class MyBatisMemberRepository implements MemberRepository {

    @Override
    public void save(Member member) {

    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void deleteById(String memberId) {

    }

    @Override
    public Optional<Member> findById(String memberId) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
