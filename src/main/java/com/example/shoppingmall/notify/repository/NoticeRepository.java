package com.example.shoppingmall.notify.repository;

import com.example.shoppingmall.notify.domain.Notice;

import java.util.List;

public interface NoticeRepository {
    List<Notice> findAllNotice();
    void addNotice(Notice notice);
    void updateNoticeByNo(int noticeNo);
    void deleteNoticeByNo(int noticeNo);
}
