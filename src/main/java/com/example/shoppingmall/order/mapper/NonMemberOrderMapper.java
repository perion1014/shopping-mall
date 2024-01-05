package com.example.shoppingmall.order.mapper;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NonMemberOrderMapper {

    void saveNonMemberOrder(NonMemberOrder nonMemberOrder);

    Long getMaxNoFromNonMemberOrderDB();

    void saveNonMemberOrderDetail(NonMemberOrderDetail nonMemberOrderDetail);

}
