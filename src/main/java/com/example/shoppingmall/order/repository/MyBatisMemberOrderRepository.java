package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.mapper.MemberOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberOrderRepository implements MemberOrderRepository{

    private final MemberOrderMapper memberOrderMapper;

    @Override   /* user */
    public void saveMemberOrder(MemberOrder memberOrder) {
        memberOrderMapper.saveMemberOrder(memberOrder);
    }

    @Override   /* user */
    public Long getMaxMemberOrderNo() {
        Long maxMemberOrderNo = memberOrderMapper.getMaxMemberOrderNo();
        return maxMemberOrderNo;
    }

    @Override   /* user */
    public void saveMemberOrderDetail(MemberOrderDetail memberOrderDetail) {
        memberOrderMapper.saveMemberOrderDetail(memberOrderDetail);
    }

    @Override   /* user */
    public List<MemberOrder> findMemberOrderList(Long memberNo) {
        List<MemberOrder> memberOrderList = memberOrderMapper.findMemberOrderList(memberNo);
        return memberOrderList;
    }

    @Override   /* user */
    public MemberOrderDetail findMemberOrderDetail(Long memberOrderNo) {
        MemberOrderDetail memberOrderDetail = memberOrderMapper.findMemberOrderDetail(memberOrderNo);
        return memberOrderDetail;
    }

    @Override   /* admin */
    public List<MemberOrder> getMemberOrderList() {
        List<MemberOrder> memberOrderList = memberOrderMapper.getMemberOrderList();
        return memberOrderList;
    }

    @Override
    public MemberOrderDetail getMemberOrderDetail() {
        MemberOrderDetail memberOrderDetail = memberOrderMapper.getMemberOrderDetail();
        return memberOrderDetail;
    }

    @Override
    public MemberOrder findMemberOrderByNo(Long memberOrderNo) {
        MemberOrder memberOrder = memberOrderMapper.findMemberOrderByNo(memberOrderNo);
        return memberOrder;
    }
}
