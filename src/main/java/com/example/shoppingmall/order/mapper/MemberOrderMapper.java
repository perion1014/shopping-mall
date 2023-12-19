package com.example.shoppingmall.order.mapper;

import com.example.shoppingmall.order.domain.MemberOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberOrderMapper {

    void saveMemberOrder(MemberOrder memberOrder);
    MemberOrder joinMemeberOrderByMemberOrderNo(Long orderNo);
}
