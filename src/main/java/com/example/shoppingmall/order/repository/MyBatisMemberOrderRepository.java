package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.form.MemberOrderAdminViewForm;
import com.example.shoppingmall.order.form.MemberOrderViewForm;
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
    public List<MemberOrder> findMemberOrderList(MemberOrderViewForm memberOrderViewForm) {
        List<MemberOrder> memberOrderList = memberOrderMapper.findMemberOrderList(memberOrderViewForm);
        return memberOrderList;
    }

    @Override   /* user */
    public List<MemberOrderDetail> findMemberOrderDetailList(Long memberOrderNo) {
        List<MemberOrderDetail> memberOrderDetailList = memberOrderMapper.findMemberOrderDetailList(memberOrderNo);
        return memberOrderDetailList;
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

    @Override
    public List<MemberOrder> findAllMemberOrdersByNo(Long memberNo) {
        List<MemberOrder> memberOrderList = memberOrderMapper.findAllMemberOrdersByNo(memberNo);
        return memberOrderList;
    }

    @Override
    public void cancelMemberOrder(Long memberOrderNo) {
        memberOrderMapper.cancelMemberOrder(memberOrderNo);
    }

    @Override
    public List<MemberOrder> findMemberOrderAdminList(MemberOrderAdminViewForm memberOrderAdminViewForm) {
        List<MemberOrder> memberOrderList = memberOrderMapper.findMemberOrderAdminList(memberOrderAdminViewForm);
        return memberOrderList;
    }
}
