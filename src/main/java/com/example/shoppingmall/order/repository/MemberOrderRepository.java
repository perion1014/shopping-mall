package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.MemberOrder;

public interface MemberOrderRepository {

    void saveMemberOrder(MemberOrder memberOrder);
}
