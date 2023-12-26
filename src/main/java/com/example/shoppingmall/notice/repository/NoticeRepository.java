package com.example.shoppingmall.notice.repository;

import com.example.shoppingmall.notice.domain.Notice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NoticeRepository {
    List<Notice> findAllNotice();
    Long countAllNotice();
    List<Notice> findAllNoticeByPaging(Map<String, Integer> pagingSettings);
    void addNotice(Notice notice);
    Optional<Notice> findByNo(Long noticeNo);
    void updateNotice(Notice notice);
    void deleteNoticeByNo(Long noticeNo);


}
