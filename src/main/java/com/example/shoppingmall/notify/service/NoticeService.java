package com.example.shoppingmall.notify.service;

import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.dto.NoticeAddDTO;
import com.example.shoppingmall.notify.dto.NoticeListDTO;
import com.example.shoppingmall.notify.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public void addNotice(NoticeAddDTO noticeAddDTO){
        Notice notice = NoticeAddDTO.noticeAddDTOToNotice(noticeAddDTO);
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


}
