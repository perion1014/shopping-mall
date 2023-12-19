package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.mapper.MemberOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberOrderRepository implements MemberOrderRepository{

    private final MemberOrderMapper memberOrderMapper;

    @Override
    public void saveMemberOrder(MemberOrder memberOrder) {
        memberOrderMapper.saveMemberOrder(memberOrder);
    }
}
