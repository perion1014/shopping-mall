package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;

import java.util.List;

public interface nonMemberOrderRepository {

    void saveNonMemberOrder(NonMemberOrder nonMemberOrder);

    Long getMaxNoFromNonMemberOrderDB();

    void saveNonMemberOrderDetail(NonMemberOrderDetail nonMemberOrderDetail);

    NonMemberOrder getNonMemberOrderFromOrderNo(Long nonMemberOrderNo, String nonMemberOrderName);

    List<NonMemberOrderDetail> getNonMemberOrderDetailFromOrderNo(Long nonMemberOrderNo);

    void cancelNonMemberOrder(Long nonMemberOrderNo);
}
