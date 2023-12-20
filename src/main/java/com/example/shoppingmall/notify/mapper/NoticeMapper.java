package com.example.shoppingmall.notify.mapper;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.dto.NoticeListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {//이곳의 이름과 쿼리 이름 동일
    List<Notice> findAllNotice();
    void addNotice(Notice notice);
    void updateNoticeByNo(Long noticeNo);
    void deleteNoticeByNo(Long noticeNo);
    Notice findByNo(Long memberNo);
}
