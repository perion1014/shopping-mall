package com.example.shoppingmall.notify.service;

import com.example.shoppingmall.member.dto.MemberDeleteDTO;
import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.dto.NoticeAddDTO;
import com.example.shoppingmall.notify.dto.NoticeDeleteDTO;
import com.example.shoppingmall.notify.dto.NoticeListDTO;
import com.example.shoppingmall.notify.dto.NoticeUpdateDTO;
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
    public void addNotice(Integer adminNo, NoticeAddDTO noticeAddDTO){
        Notice notice = NoticeAddDTO.NoticeAddDTOToNotice(adminNo, noticeAddDTO);
        System.out.println("service_notice.getAdminNo() = " + notice.getAdminNo());
        noticeRepository.addNotice(notice);
    }
    @Transactional
    public NoticeUpdateDTO getNoticeInfo(Long noticeNo){
        Notice notice = noticeRepository.findByNo(noticeNo).orElse(null);
        return NoticeUpdateDTO.NoticeToNoticeUpdateDTO(notice);

    }

    @Transactional
    public void update(){

    }
    @Transactional
    public void deleteNotice(Long noticeNo){
        noticeRepository.deleteNoticeByNo(noticeNo);
    }


}
