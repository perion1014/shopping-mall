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
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberOrderService {

    private final MemberOrderRepository memberOrderRepository;

    /* user */
    public void saveMemberOrder(Long memberNo, MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrder memberOrder = MemberOrderAddDTO.toMemberOrder(memberNo, memberOrderAddDTO);
        //MemberOrderDetail memberOrderDetail = MemberOrderAddDTO.toMemberOrderDetail(memberOrderAddDTO);
        memberOrderRepository.saveMemberOrder(memberOrder);
        //memberOrderRepository.saveMemberOrderDetail(memberOrderDetail);
    }

//    public void saveMemberOrderDetail(MemberOrderDetailAddDTO memberOrderDetailAddDTO) {
//        MemberOrderDetail memberOrderDetail = MemberOrderDetailAddDTO.toMemberOrderDetail(memberOrderDetailAddDTO);
//        memberOrderRepository.saveMemberOrderDetail(memberOrderDetail);
//    }

    /* user */
    public Long getMaxMemberOrderNo() {
        Long maxMemberOrderNo = memberOrderRepository.getMaxMemberOrderNo();
        return maxMemberOrderNo;
    }

    /* user */
    public void saveMemberOrderDetail(Long maxMemberOrderNo, List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList) {
        List<MemberOrderDetail> memberOrderDetailList = new ArrayList<>();
        for (MemberOrderDetailAddDTO memberOrderDetailAddDTO: memberOrderDetailAddDTOList) {
            MemberOrderDetail memberOrderDetail = new MemberOrderDetail();
            memberOrderDetail.setMemberOrderDetailNo(memberOrderDetailAddDTO.getMemberOrderDetailNo());
            memberOrderDetail.setMemberOrderNo(maxMemberOrderNo);
            memberOrderDetail.setItemNo(memberOrderDetailAddDTO.getItemNo());
            memberOrderDetail.setItemQuantity(memberOrderDetailAddDTO.getItemQuantity());
            memberOrderDetail.setItemSize(memberOrderDetailAddDTO.getItemSize());
            memberOrderDetailList.add(memberOrderDetail);
        }
        for (MemberOrderDetail memberOrderDetail: memberOrderDetailList) {
            memberOrderRepository.saveMemberOrderDetail(memberOrderDetail);
        }
        MemberOrder memberOrder = memberOrderRepository.findMemberOrderByNo(maxMemberOrderNo);
        memberOrder.setMemberOrderDetailList(memberOrderDetailList);
    }


    /* user */
    public List<MemberOrderDTO> findMemberOrderList(Long memberNo) {
        List<MemberOrder> memberOrderList = memberOrderRepository.findMemberOrderList(memberNo);
        List<MemberOrderDTO> memberOrderDTOList = new ArrayList<>();
        for (MemberOrder memberOrder: memberOrderList) {
            memberOrderDTOList.add(MemberOrderDTO.toMemberOrderDTO(memberOrder));
        }
        return  memberOrderDTOList;
    }

    /* user */
    public MemberOrderDetailDTO findMemberOrderDetail(Long memberOrderNo) {
        MemberOrderDetail memberOrderDetail = memberOrderRepository.findMemberOrderDetail(memberOrderNo);
        MemberOrderDetailDTO memberOrderDetailDTO = MemberOrderDetailDTO.toMemberOrderDetailDTO(memberOrderDetail);
        return memberOrderDetailDTO;
    }



    /* admin */
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
