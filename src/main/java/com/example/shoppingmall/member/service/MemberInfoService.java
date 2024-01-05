package com.example.shoppingmall.member.service;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberIdDTO;
import com.example.shoppingmall.member.dto.MemberLoginDTO;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
import com.example.shoppingmall.member.dto.MemberUpdateDTO;
import com.example.shoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberIdDTO getMemberIdByEmail(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail).orElse(null);
        return  MemberIdDTO.MemberToMemberIdDTO(member);
    }
}
