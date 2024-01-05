package com.example.shoppingmall.order.repository;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import com.example.shoppingmall.order.mapper.NonMemberOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisNonmemberOrderRepository implements nonMemberOrderRepository {

    private final NonMemberOrderMapper nonMemberOrderMapper;

    @Override
    public void saveNonMemberOrder(NonMemberOrder nonMemberOrder) {nonMemberOrderMapper.saveNonMemberOrder(nonMemberOrder); }

    @Override
    public Long getMaxNoFromNonMemberOrderDB(){return nonMemberOrderMapper.getMaxNoFromNonMemberOrderDB();}

    @Override
    public void saveNonMemberOrderDetail(NonMemberOrderDetail nonMemberOrderDetail){nonMemberOrderMapper.saveNonMemberOrderDetail(nonMemberOrderDetail);}

}
