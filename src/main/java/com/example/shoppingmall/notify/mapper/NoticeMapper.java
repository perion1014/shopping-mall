package com.example.shoppingmall.notify.mapper;

import com.example.shoppingmall.notify.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> findAllNotice();
    void addNotice(Notice notice);
    void updateNoticeByNo(int noticeNo);
    void deleteNoticeByNo(int noticeNo);
}
