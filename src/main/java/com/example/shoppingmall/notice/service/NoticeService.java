package com.example.shoppingmall.notice.service;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.notice.domain.Notice;
import com.example.shoppingmall.notice.dto.*;
import com.example.shoppingmall.notice.form.NoticePageForm;
import com.example.shoppingmall.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    //전체 공지 조회
    @Transactional(readOnly = true)
    public List<NoticeListDTO> findAllNotice() {
        List<Notice> noticeList = noticeRepository.findAllNotice();
        List<NoticeListDTO> noticeListDTOList = new ArrayList<>();
        for (Notice notice : noticeList) {
            noticeListDTOList.add(NoticeListDTO.NoticeToNoticeListDTO(notice));
        }
        return noticeListDTOList;
    }

    //공지 추가
    @Transactional
    public void addNotice(Integer adminNo, NoticeAddDTO noticeAddDTO) {
        Notice notice = NoticeAddDTO.NoticeAddDTOToNotice(adminNo, noticeAddDTO);
        System.out.println("service_notice.getAdminNo() = " + notice.getAdminNo());
        noticeRepository.addNotice(notice);
    }

    //공지내용
    @Transactional
    public NoticeUpdateDTO getNoticeInfo(Long noticeNo) {
        Notice notice = noticeRepository.findByNo(noticeNo).orElse(null);
//        notice.setNoticeViewcount(notice.getNoticeViewcount());
        noticeRepository.updateViewCount(noticeNo);
        return NoticeUpdateDTO.NoticeToNoticeUpdateDTO(notice);

    }

    //공지 수정
    @Transactional
    public void update(NoticeUpdateDTO noticeUpdateDTO) {
        Notice notice = NoticeUpdateDTO.NoticeUpdateDTOToNotice(noticeUpdateDTO);
        noticeRepository.updateNotice(notice);
        System.out.println("noticeUpdateDTO.getNoticeNo() = " + noticeUpdateDTO.getNoticeNo());

    }

    //공지 삭제
    @Transactional
    public void deleteNotice(Long noticeNo) {
        noticeRepository.deleteNoticeByNo(noticeNo);
    }

    //공지 조회 페이지 설정(보여줄 엔테티티 수 , 페이지 당 페이지수)
    @Transactional(readOnly = true)
    public NoticePageForm setNoticeListPage(int page) {

        int pagePerNotice = 12;//보여줄 공지 수
        int pageLimit = 10;//하단 페이징 번호 갯수

        //전체 공지 조회
        Long noticeCount = noticeRepository.countAllNotice();

        //전체 페이지 갯수 계산(10/3.3333 => 4)
        int totalPage = (int) (Math.ceil((double) noticeCount / pagePerNotice));

        //시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int) (Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        //끝 페이지 값 계산(3, 6, 9, 12, ~~~)
        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        return new NoticePageForm(page, totalPage, startPage, endPage);
    }

    //공지 조회 페이지 설정
    @Transactional(readOnly = true)
    public List<NoticeSearchDTO> getNoticeListPage(int page){

        int startPage = (page-1) * 12;//시작페이지
        int pagePerNotice = 12;//멤버 수

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("pagePerNotice", pagePerNotice);

        System.out.println("startPage = " + startPage);
        System.out.println("pagePerNotice = " + pagePerNotice);

        List<Notice> noticeList = noticeRepository.findAllNoticeByPaging(pagingSettings);

        List<NoticeSearchDTO> resultList = new ArrayList<>();

        for(Notice notice : noticeList){
            resultList.add(NoticeSearchDTO.NoticeToNoticeSearchDTO(notice));
        }
        return resultList;
    }

}

