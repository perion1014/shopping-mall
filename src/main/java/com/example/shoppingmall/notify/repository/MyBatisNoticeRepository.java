package com.example.shoppingmall.notify.repository;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisNoticeRepository implements NoticeRepository{

    private final NoticeMapper noticeMapper;
    @Override
    public List<Notice> findAllNotice() {//Notice
        return noticeMapper.findAllNotice();
    }
    @Override
    public void addNotice(Notice notice){
        noticeMapper.addNotice(notice);
    }
    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }
    @Override
    public void deleteNoticeByNo(Long noticeNo) {
        noticeMapper.deleteNoticeByNo(noticeNo);
    }
    @Override
    public Optional<Notice> findByNo(Long noticeNo) {
        return Optional.ofNullable(noticeMapper.findByNo(noticeNo));
    }
}
