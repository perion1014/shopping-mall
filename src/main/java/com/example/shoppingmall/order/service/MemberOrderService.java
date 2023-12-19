package com.example.shoppingmall.order.service;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.dto.MemberOrderAddDTO;
import com.example.shoppingmall.order.repository.MemberOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberOrderService {

    private final MemberOrderRepository memberOrderRepository;

    public void saveMemberOrder(MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrder memberOrder = MemberOrderAddDTO.toMemberOrder(memberOrderAddDTO);
        memberOrderRepository.saveMemberOrder(memberOrder);
    }
}
