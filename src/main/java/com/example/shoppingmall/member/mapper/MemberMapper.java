package com.example.shoppingmall.member.mapper;

import com.example.shoppingmall.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    void save(Member member);
    void update(Member member);
    void deleteById(String memberId);
    Member findById(String memberId);
    Member findByEmail(String memberEmail);
    List<Member> findAll();
}
