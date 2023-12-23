package com.example.shoppingmall.member.service;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.*;
import com.example.shoppingmall.member.form.MemberPageForm;
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


    /*회원 가입*/
    @Transactional
    public void join(MemberAddDTO memberAddDTO){
        Member member = MemberAddDTO.MemberAddDTOToMember(memberAddDTO);
        memberRepository.findById(member.getMemberId())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 아이디입니다.");});
        memberRepository.findByEmail(member.getMemberEmail())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 이메일입니다.");});
        memberRepository.save(member);
    }

    /*유저 회원 정보 조회*/
    @Transactional(readOnly = true)
    public MemberUpdateDTO getMemberInfo(Long memberNo){
        Member member = memberRepository.findByNo(memberNo)
                        .orElse(null);
        return MemberUpdateDTO.MemberToMemberUpdateDTO(member);
    }

    /*유저 회원 정보 수정*/
    @Transactional
    public void update(MemberUpdateDTO memberUpdateDTO){
        Member member = MemberUpdateDTO.MemberUpdateDTOToMember(memberUpdateDTO);
        memberRepository.update(member);
    }

    /*유저 회원 탈퇴*/
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

    /*관리자 회원 삭제*/
    @Transactional
    public void drop(Long memberNo){
        memberRepository.deleteByNo(memberNo);
    }

    /*관리자 회원 조회 페이지 설정 (보여줄 엔티티 수, 페이지 당 페이지 수) */
    @Transactional(readOnly = true)
    public MemberPageForm setMemberListPage(int page) {

        int pagePerMember = 12; // 보여줄 멤버 수
        int pageLimit = 10; // 하단 페이징 번호 갯수

        // 전체 멤버 조회
        Long memberCount = memberRepository.countAll();

        // 전체 페이지 갯수 계산 (10/3=3.33333 => 4)
        int totalPage = (int) (Math.ceil((double) memberCount / pagePerMember));

        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new MemberPageForm(page,totalPage,startPage,endPage);
    }

    /*관리자 회원 조회 페이지 생성 */
    @Transactional(readOnly = true)
    public List<MemberSearchDTO> getMemberListPage(int page) {

        int startPage = (page-1) * 12; //시작 페이지
        int pagePerMember = 12; // 멤버 수

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("pagePerMember", pagePerMember);
        List<Member> memberList = memberRepository.findAllByPaging(pagingSettings);

        /**
         * Repository에서 반환받은 Member를  MemberListDTO로 변환하는 로직 추가
         * for-each문에서 MemberListDTO의 static 메소드로 변환 후
         * 빈 List<MemberListDto> 에 add한다.
         *
         * @author HyonHyon
         * @return List<Member> -> List<MemberListDTO>
         */

        List<MemberSearchDTO> resultList = new ArrayList<>();

        for(Member member : memberList){
            resultList.add(MemberSearchDTO.fromEntity(member));
        }

        return resultList;
    }


    /*관리자 회원 검색*/
    @Transactional(readOnly = true)
    public List<MemberSearchDTO> searchMemberInfo(MemberSearchForm memberSearchForm){
        String category = memberSearchForm.getCategory();
        String keyword = memberSearchForm.getKeyword();

        List<Member> memberList = new ArrayList<>();
        List<MemberSearchDTO> resultList = new ArrayList<>();

        if(!category.equals("memberNo")){
            Map<String,String> searchingKeyword = new HashMap<>();
            searchingKeyword.put("category", category);
            searchingKeyword.put("keyword", keyword);
            memberList = memberRepository.findByKeyword(searchingKeyword);
        }
        else{
            memberList = memberRepository.findByNoContaining(Long.parseLong(keyword));
        }

        for(Member member : memberList){
            resultList.add(MemberSearchDTO.fromEntity(member));
        }

        return resultList;
    }

    // 2023-12-22
    @Transactional(readOnly = true)
    public List<MemberSearchDTO> getSearchMemberListPage(int page, MemberSearchForm memberSearchForm) {

        int startPage = (page-1) * 12; //시작 페이지
        int pagePerMember = 12; // 멤버 수

        memberSearchForm.setStart(startPage);
        memberSearchForm.setLimit(pagePerMember);
        List<Member> memberList = memberRepository.findByKeyword2(memberSearchForm);
        System.out.println(memberSearchForm.getKeyword());
        System.out.println("memberSearchForm.getCategory() = " + memberSearchForm.getCategory());
        List<MemberSearchDTO> resultList = new ArrayList<>();


        for(Member member : memberList){
            resultList.add(MemberSearchDTO.fromEntity(member));
        }

        return resultList;
    }

    @Transactional(readOnly = true)
    public MemberPageForm setMemberListPage2(int page, MemberSearchForm memberSearchForm) {

        int pagePerMember = 12; // 보여줄 멤버 수
        int pageLimit = 10; // 하단 페이징 번호 갯수

        // 전체 멤버 조회
        Long memberCount = memberRepository.countAllByKeyword2(memberSearchForm);

        // 전체 페이지 갯수 계산 (10/3=3.33333 => 4)
        int totalPage = (int) (Math.ceil((double) memberCount / pagePerMember));

        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new MemberPageForm(page,totalPage,startPage,endPage);
    }

}

