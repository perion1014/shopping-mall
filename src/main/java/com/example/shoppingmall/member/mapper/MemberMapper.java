package com.example.shoppingmall.member.mapper;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    void save(Member member);
    void update(Member member);
    void deleteByNo(Long memberNo);
    Member findByNo(Long memberNo);
    Member findById(String memberId);
    Member findByEmail(String memberEmail);
    List<Member> findByNoContaining(Long memberNo);
    List<Member> findByIdContaining(String memberId);
    List<Member> findByHpContaining(String memberHp);
    List<Member> findByEmailContaining(String memberEmail);
    List<Member> findByNameContaining(String memberName);
    List<Member> findByAddressBasicContaining(String memberAddressBasic);


    List<Member> findAll();

    // 김찬혁 페이징
    List<MemberListDTO> pagingList(Map<String, Integer> pagingParams);
    Long memberCount();
}
