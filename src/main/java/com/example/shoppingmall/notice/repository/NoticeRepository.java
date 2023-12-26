package com.example.shoppingmall.notice.repository;

import com.example.shoppingmall.notice.domain.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository {
    List<Notice> findAllNotice();
    void addNotice(Notice notice);
    Optional<Notice> findByNo(Long noticeNo);
    void updateNotice(Notice notice);
    void deleteNoticeByNo(Long noticeNo);


}
