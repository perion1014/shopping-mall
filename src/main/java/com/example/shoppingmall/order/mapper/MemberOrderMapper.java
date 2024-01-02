package com.example.shoppingmall.order.mapper;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MemberOrderMapper {

    /* user */
    void saveMemberOrder(MemberOrder memberOrder);

    /* user */
    void saveMemberOrderDetail(MemberOrderDetail memberOrderDetail);

    /* user */
    List<MemberOrder> findMemberOrderList(Long memberNo);

    /* user */
    MemberOrderDetail findMemberOrderDetail(Long memberOrderNo);

    /* admin */
    List<MemberOrder> getMemberOrderList();

    MemberOrderDetail getMemberOrderDetail();

    MemberOrder joinMemeberOrderByMemberOrderNo(Long orderNo);



}
