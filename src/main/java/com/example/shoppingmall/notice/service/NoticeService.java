package com.example.shoppingmall.notice.service;

import com.example.shoppingmall.notice.domain.Notice;
import com.example.shoppingmall.notice.dto.*;
import com.example.shoppingmall.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    //전체 공지 조회
    @Transactional(readOnly = true)
    public List<NoticeListDTO> findAllNotice(){
        List<Notice> noticeList = noticeRepository.findAllNotice();
        List<NoticeListDTO> noticeListDTOList = new ArrayList<>();
        for(Notice notice : noticeList){
            noticeListDTOList.add(NoticeListDTO.NoticeToNoticeListDTO(notice));
        }
        return noticeListDTOList;
    }
    //공지 추가
    @Transactional
    public void addNotice(Integer adminNo, NoticeAddDTO noticeAddDTO){
        Notice notice = NoticeAddDTO.NoticeAddDTOToNotice(adminNo, noticeAddDTO);
        System.out.println("service_notice.getAdminNo() = " + notice.getAdminNo());
        noticeRepository.addNotice(notice);
    }
    //공지내용
    @Transactional
    public NoticeUpdateDTO getNoticeInfo(Long noticeNo){
        Notice notice = noticeRepository.findByNo(noticeNo).orElse(null);
        return NoticeUpdateDTO.NoticeToNoticeUpdateDTO(notice);

    }
    //공지 수정
    @Transactional
    public void update(NoticeUpdateDTO noticeUpdateDTO){
        Notice notice = NoticeUpdateDTO.NoticeUpdateDTOToNotice(noticeUpdateDTO);
        noticeRepository.updateNotice(notice);
    }
    //공지 삭제
    @Transactional
    public void deleteNotice(Long noticeNo){
        noticeRepository.deleteNoticeByNo(noticeNo);
    }
}
