package com.example.shoppingmall.member.service;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberAddDTO;
import com.example.shoppingmall.member.dto.MemberDeleteDTO;
import com.example.shoppingmall.member.dto.MemberListDTO;
import com.example.shoppingmall.member.dto.MemberUpdateDTO;
import com.example.shoppingmall.member.form.MemberSearchForm;
import com.example.shoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberAddDTO memberAddDTO){
        Member member = MemberAddDTO.MemberAddDTOToMember(memberAddDTO);
        memberRepository.findById(member.getMemberId())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 아이디입니다.");});
        memberRepository.findByEmail(member.getMemberEmail())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 이메일입니다.");});
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberUpdateDTO getMemberInfo(Long memberNo){
        Member member = memberRepository.findByNo(memberNo)
                        .orElse(null);
        return MemberUpdateDTO.MemberToMemberUpdateDTO(member);
    }

    @Transactional(readOnly = true)
    public List<MemberListDTO> searchAllMemberInfo(){
        List<Member> memberList= memberRepository.findAll();
        List<MemberListDTO> memberListDTOList = new ArrayList<>();
        for(Member member : memberList){
            memberListDTOList.add(MemberListDTO.MemberToMemberListDTO(member));
        }
        return memberListDTOList;
    }

    @Transactional
    public void update(MemberUpdateDTO memberUpdateDTO){
        Member member = MemberUpdateDTO.MemberUpdateDTOToMember(memberUpdateDTO);
        memberRepository.update(member);
    }

    @Transactional
    public void withdraw(Long memberNo,MemberDeleteDTO memberDeleteDTO){
        Member member = MemberDeleteDTO.MemberDeleteDTOToMember(memberNo,memberDeleteDTO);

        memberRepository.findByNo(member.getMemberNo())
                .filter(m -> m.getMemberPw().equals(member.getMemberPw()))
                .ifPresentOrElse(
                m -> memberRepository.deleteByNo(member.getMemberNo()),
                () -> { throw new IllegalStateException("비밀번호가 일치하지 않습니다."); }
        );
    }

    @Transactional
    public void drop(Long memberNo){
        memberRepository.deleteByNo(memberNo);
    }

    @Transactional(readOnly = true)
    public List<MemberListDTO> searchMemberInfo(MemberSearchForm memberSearchForm){
        String category = memberSearchForm.getCategory();
        String keyword = memberSearchForm.getKeyword();

        List<Member> memberList = new ArrayList<>();
        List<MemberListDTO> memberListDTOList = new ArrayList<>();

        switch(category){
            case "memberNo":
                memberList = memberRepository.findByNoContaining(Long.parseLong(keyword));
                break;
            case "memberId":
                memberList = memberRepository.findByIdContaining(keyword);
                break;
            case "memberHp":
                memberList = memberRepository.findByHpContaining(keyword);
                break;
            case "memberEmail":
                memberList = memberRepository.findByEmailContaining(keyword);
                break;
            case "memberName":
                memberList = memberRepository.findByNameContaining(keyword);
                break;
            case "memberAddressBasic":
                memberList = memberRepository.findByAddressBasicContaining(keyword);
                break;
        }

        for(Member member : memberList){
            memberListDTOList.add(MemberListDTO.MemberToMemberListDTO(member));
            System.out.println("member = " + member);
        }

        return memberListDTOList;
    }
}
