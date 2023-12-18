package com.example.shoppingmall.member.service;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberLoginDTO;
import com.example.shoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member login(MemberLoginDTO memberLoginDTO) {
        Member member = MemberLoginDTO.MemberLoginDTOToMember(memberLoginDTO);
        return memberRepository.findById(member.getMemberId())
                .filter(m-> m.getMemberPw().equals(member.getMemberPw()))
                .orElse(null);
    }

}
