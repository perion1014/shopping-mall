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
        memberMapper.update(member);
    }

    @Override
    public void deleteByNo(Long memberNo) {
        memberMapper.deleteByNo(memberNo);
    }

    @Override
    public Optional<Member> findByNo(Long memberNo) {
        return Optional.ofNullable(memberMapper.findByNo(memberNo));
    }

    @Override
    public Optional<Member> findById(String memberId) {
        return Optional.ofNullable(memberMapper.findById(memberId));
    }

    @Override
    public Optional<Member> findByEmail(String memberEmail) {
        return Optional.ofNullable(memberMapper.findByEmail(memberEmail));
    }

    @Override
    public List<Member> findByNoContaining(Long memberNo) {
        return memberMapper.findByNoContaining(memberNo);
    }

    @Override
    public List<Member> findByIdContaining(String memberId) {
        return memberMapper.findByIdContaining(memberId);
    }

    @Override
    public List<Member> findByHpContaining(String memberHp) {
        return memberMapper.findByHpContaining(memberHp);
    }

    @Override
    public List<Member> findByEmailContaining(String memberEmail) {
        return memberMapper.findByEmailContaining(memberEmail);
    }

    @Override
    public List<Member> findByNameContaining(String memberName) {
        return memberMapper.findByNameContaining(memberName);
    }

    @Override
    public List<Member> findByAddressBasicContaining(String memberAddressBasic) {
        return memberMapper.findByAddressBasicContaining(memberAddressBasic);
    }

    @Override
    public List<Member> findAll() {
         return memberMapper.findAll();
    }
}
