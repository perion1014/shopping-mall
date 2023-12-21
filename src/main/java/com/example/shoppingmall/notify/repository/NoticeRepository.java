package com.example.shoppingmall.notify.repository;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.dto.NoticeListDTO;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository {
    List<Notice> findAllNotice();
    void addNotice(Notice notice);
    void updateNotice(Notice notice);
    void deleteNoticeByNo(Long noticeNo);
    Optional<Notice> findByNo(Long noticeNo);

}
