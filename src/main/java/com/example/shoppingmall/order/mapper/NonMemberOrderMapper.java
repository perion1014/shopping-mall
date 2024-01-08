package com.example.shoppingmall.order.mapper;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NonMemberOrderMapper {

    void saveNonMemberOrder(NonMemberOrder nonMemberOrder);

    Long getMaxNoFromNonMemberOrderDB();

    void saveNonMemberOrderDetail(NonMemberOrderDetail nonMemberOrderDetail);

    NonMemberOrder getNonMemberOrderFromOrderNo(@Param("nonMemberOrderNo") Long nonMemberOrderNo, @Param("nonMemberOrderName") String nonMemberOrderName);

    List<NonMemberOrderDetail> getNonMemberOrderDetailFromOrderNo(@Param("nonMemberOrderNo")Long nonMemberOrderNo);

    void cancelNonMemberOrder(@Param("nonMemberOrderNo") Long nonMemberOrderNo);

}
