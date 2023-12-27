package com.example.shoppingmall.notice.repository;

import com.example.shoppingmall.notice.domain.Notice;
import com.example.shoppingmall.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
    public Long countAllNotice(){
        return noticeMapper.countAllNotice();
    }
    @Override
    public List<Notice>findAllNoticeByPaging(Map<String, Integer> pagingSettings){
        return noticeMapper.findAllNoticeByPaging(pagingSettings);
    }
    @Override
    public void addNotice(Notice notice){
        noticeMapper.addNotice(notice);
    }
    @Override
    public Optional<Notice> findByNo(Long noticeNo) {
        return Optional.ofNullable(noticeMapper.findByNo(noticeNo));
    }
    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }
    @Override
    public void updateViewCount(Long noticeNo){
        noticeMapper.updateViewCount(noticeNo);
    }
    @Override
    public void deleteNoticeByNo(Long noticeNo) {
        noticeMapper.deleteNoticeByNo(noticeNo);
    }


}
