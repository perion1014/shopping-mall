package com.example.shoppingmall.member.repository;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
import com.example.shoppingmall.member.form.MemberSearchForm;
import com.example.shoppingmall.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
    public Optional<Member> findByHp(String memberHp) {
        return Optional.ofNullable(memberMapper.findByHp(memberHp));
    }

    @Override
    public List<Member> findAllByPaging(Map<String, Integer> pagingSettings) {
        return memberMapper.findAllByPaging(pagingSettings);
    }

    @Override
    public Long countAll() {
        return memberMapper.countAll();
    }

    @Override
    public Long countAllByKeyword(MemberSearchForm memberSearchForm) {
        return memberMapper.countAllByKeyword(memberSearchForm);
    }

    @Override
    public List<Member> findAllByKeyword(MemberSearchForm memberSearchForm) {
        return memberMapper.findAllByKeyword(memberSearchForm);
    }



}
