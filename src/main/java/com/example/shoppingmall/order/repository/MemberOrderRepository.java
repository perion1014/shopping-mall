package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;

import java.util.List;

public interface MemberOrderRepository {

    void saveMemberOrder(MemberOrder memberOrder);

    void saveMemberOrderDetail(MemberOrderDetail memberOrderDetail);

    List<MemberOrder> getMemberOrderList();

    MemberOrderDetail getMemberOrderDetail();
}
