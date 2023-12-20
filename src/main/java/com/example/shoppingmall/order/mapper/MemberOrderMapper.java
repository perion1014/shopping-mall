package com.example.shoppingmall.order.mapper;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberOrderMapper {

    void saveMemberOrder(MemberOrder memberOrder);

    void saveMemberOrderDetail(MemberOrderDetail memberOrderDetail);

    List<MemberOrder> getMemberOrderList();

    MemberOrderDetail getMemberOrderDetail();

    MemberOrder joinMemeberOrderByMemberOrderNo(Long orderNo);


}
