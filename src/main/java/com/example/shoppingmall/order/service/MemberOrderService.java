package com.example.shoppingmall.order.service;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.dto.MemberOrderAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailDTO;
import com.example.shoppingmall.order.repository.MemberOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberOrderService {

    private final MemberOrderRepository memberOrderRepository;

    public void saveMemberOrder(MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrder memberOrder = MemberOrderAddDTO.toMemberOrder(memberOrderAddDTO);
        memberOrderRepository.saveMemberOrder(memberOrder);
    }

    public void saveMemberOrderDetail(MemberOrderDetailAddDTO memberOrderDetailAddDTO) {
        MemberOrderDetail memberOrderDetail = MemberOrderDetailAddDTO.toMemberOrderDetail(memberOrderDetailAddDTO);
        memberOrderRepository.saveMemberOrderDetail(memberOrderDetail);
    }

    public List<MemberOrderDTO> getMemberOrderList() {
        List<MemberOrder> memberOrderList = memberOrderRepository.getMemberOrderList();
        List<MemberOrderDTO> memberOrderDTOList = new ArrayList<>();
        for (MemberOrder memberOrder: memberOrderList) {
            memberOrderDTOList.add(MemberOrderDTO.toMemberOrderDTO(memberOrder));
        }
        return memberOrderDTOList;
    }

    public MemberOrderDetailDTO getMemberOrderDetail() {
        MemberOrderDetail memberOrderDetail = memberOrderRepository.getMemberOrderDetail();
        MemberOrderDetailDTO memberOrderDetailDTO = MemberOrderDetailDTO.toMemberOrderDetailDTO(memberOrderDetail);
        return memberOrderDetailDTO;
    }
}
