package com.example.shoppingmall.notify.mapper;

import com.example.shoppingmall.notify.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    Notice findAllNotice();
}
