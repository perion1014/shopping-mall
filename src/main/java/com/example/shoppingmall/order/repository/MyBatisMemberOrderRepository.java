package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.mapper.MemberOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberOrderRepository implements MemberOrderRepository{

    private final MemberOrderMapper memberOrderMapper;

    @Override
    public void saveMemberOrder(MemberOrder memberOrder) {
        memberOrderMapper.saveMemberOrder(memberOrder);
    }

    @Override
    public void saveMemberOrderDetail(MemberOrderDetail memberOrderDetail) {
        memberOrderMapper.saveMemberOrderDetail(memberOrderDetail);
    }

    @Override
    public List<MemberOrder> getMemberOrderList() {
        List<MemberOrder> memberOrderList = memberOrderMapper.getMemberOrderList();
        return memberOrderList;
    }

    @Override
    public MemberOrderDetail getMemberOrderDetail() {
        MemberOrderDetail memberOrderDetail = memberOrderMapper.getMemberOrderDetail();
        return memberOrderDetail;
    }
}
