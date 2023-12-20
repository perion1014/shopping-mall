package com.example.shoppingmall.notify.service;

import com.example.shoppingmall.member.dto.MemberDeleteDTO;
import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.dto.NoticeAddDTO;
import com.example.shoppingmall.notify.dto.NoticeDeleteDTO;
import com.example.shoppingmall.notify.dto.NoticeListDTO;
import com.example.shoppingmall.notify.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    @Transactional
    public void addNotice(Integer adminNo, NoticeAddDTO noticeAddDTO){
        Notice notice = NoticeAddDTO.NoticeAddDTOToNotice(adminNo, noticeAddDTO);
        System.out.println("service_notice.getAdminNo() = " + notice.getAdminNo());
        noticeRepository.addNotice(notice);
    }
    @Transactional(readOnly = true)
    public List<NoticeListDTO> findAllNotice(){
        List<Notice> noticeList = noticeRepository.findAllNotice();
        List<NoticeListDTO> noticeListDTOList = new ArrayList<>();
        for(Notice notice : noticeList){
            noticeListDTOList.add(NoticeListDTO.NoticeToNoticeListDTO(notice));
        }
    return noticeListDTOList;
    }
    @Transactional
    public void deleteNotice(Long noticeNo, NoticeDeleteDTO noticeDeleteDTO){
        Notice notice = NoticeDeleteDTO.NoticeDeleteDTOToNotice(noticeNo, noticeDeleteDTO);
        noticeRepository.findByNo(notice.getNoticeNo());

    }


}
