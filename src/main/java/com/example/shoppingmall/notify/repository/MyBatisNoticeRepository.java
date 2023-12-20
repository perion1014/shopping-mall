package com.example.shoppingmall.notify.repository;

import com.example.shoppingmall.notify.domain.Notice;
import com.example.shoppingmall.notify.dto.NoticeListDTO;
import com.example.shoppingmall.notify.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void updateNoticeByNo(Integer noticeNo) {

    }

    @Override
    public void deleteNoticeByNo(Integer noticeNo) {

    }
}
