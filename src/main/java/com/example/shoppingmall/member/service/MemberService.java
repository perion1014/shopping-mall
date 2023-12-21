package com.example.shoppingmall.member.service;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.*;
import com.example.shoppingmall.member.form.MemberSearchForm;
import com.example.shoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    int pageLimit = 12;
    int blockLimit = 5 ;

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
        }

        return memberListDTOList;
    }

    @Transactional
    public List<MemberListDTO> pagingList(int page) {

        int pagingStart = (page-1) * 12;

        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);

        List<MemberListDTO> pagingList = memberRepository.pagingList(pagingParams);

        return pagingList;
    }

    @Transactional
    public MemberPageDTO pagingParam(int page) {

        // 전체 글 갯수 조회
        int memberCount = memberRepository.memberCount();

        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) memberCount / pageLimit));

        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;

        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }

        MemberPageDTO pageDTO = new MemberPageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }
}

