package com.example.shoppingmall.notify.repository;

import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.dto.NoticeListDTO;

import java.util.List;

public interface NoticeRepository {
    List<NoticeListDTO> findAllNotice();
    void addNotice(Notice notice);
    void updateNoticeByNo(Integer noticeNo);
    void deleteNoticeByNo(Integer noticeNo);
}
