package com.example.shoppingmall.member.repository;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public void save(Member member) {
       memberMapper.save(member);
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
