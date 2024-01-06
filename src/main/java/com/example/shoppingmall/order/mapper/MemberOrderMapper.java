package com.example.shoppingmall.order.mapper;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.form.MemberOrderAdminViewForm;
import com.example.shoppingmall.order.form.MemberOrderViewForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MemberOrderMapper {

    /* user */
    void saveMemberOrder(MemberOrder memberOrder);

    /* user */
    Long getMaxMemberOrderNo();

    /* user */
    void saveMemberOrderDetail(MemberOrderDetail memberOrderDetail);

    /* user */
    List<MemberOrder> findMemberOrderList(MemberOrderViewForm memberOrderViewForm);

    /* user */
    List<MemberOrderDetail> findMemberOrderDetailList(Long memberOrderNo);

    /* admin */
    List<MemberOrder> getMemberOrderList();

    MemberOrderDetail getMemberOrderDetail();

    MemberOrder joinMemeberOrderByMemberOrderNo(Long orderNo);


    /* user */
    MemberOrder findMemberOrderByNo(Long memberOrderNo);


    List<MemberOrder> findAllMemberOrdersByNo(Long memberNo);

    /* user */
    void cancelMemberOrder(Long memberOrderNo);


    List<MemberOrder> findMemberOrderAdminList(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListBySearch(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListPageBySearch(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListBySearch2(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListPageBySearch2(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListBySearch3(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListPageBySearch3(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListBySearchLong(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListPageBySearchLong(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListBySearchLong2(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListPageBySearchLong2(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListBySearchInteger(MemberOrderAdminViewForm memberOrderAdminViewForm);

    List<MemberOrder> getMemberOrderListPageBySearchInteger(MemberOrderAdminViewForm memberOrderAdminViewForm);
}
