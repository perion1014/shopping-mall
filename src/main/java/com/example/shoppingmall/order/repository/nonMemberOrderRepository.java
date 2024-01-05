package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;

public interface nonMemberOrderRepository {

    void saveNonMemberOrder(NonMemberOrder nonMemberOrder);

    Long getMaxNoFromNonMemberOrderDB();

    void saveNonMemberOrderDetail(NonMemberOrderDetail nonMemberOrderDetail);
}
