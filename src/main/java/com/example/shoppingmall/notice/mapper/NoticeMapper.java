package com.example.shoppingmall.notice.mapper;

import com.example.shoppingmall.notice.domain.Notice;
import com.example.shoppingmall.notice.form.NoticeSearchFrom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {//이곳의 이름과 쿼리 이름 동일
    List<Notice> findAllNotice();
    Long countAllNotice();
    List<Notice>findAllNoticeByPaging(Map<String, Integer> pagingSettings);
    void addNotice(Notice notice);
    void updateNotice(Notice notice);
    void updateViewCount(Long noticeNo);
    void deleteNoticeByNo(Long noticeNo);
    Notice findByNo(Long memberNo);
    Long countAllNoticeByKeyword(NoticeSearchFrom noticeSearchFrom);
    List<Notice> findAllNoticeByKeyword(NoticeSearchFrom noticeSearchFrom);
}
