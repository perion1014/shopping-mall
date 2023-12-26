package com.example.shoppingmall.notice.mapper;

import com.example.shoppingmall.notice.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {//이곳의 이름과 쿼리 이름 동일
    List<Notice> findAllNotice();
    void addNotice(Notice notice);
    void updateNotice(Notice notice);
    void deleteNoticeByNo(Long noticeNo);
    Notice findByNo(Long memberNo);
}
