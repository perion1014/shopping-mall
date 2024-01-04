package com.example.shoppingmall.order.service;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.dto.MemberOrderAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailDTO;
import com.example.shoppingmall.order.form.MemberOrderPageForm;
import com.example.shoppingmall.order.form.MemberOrderViewForm;
import com.example.shoppingmall.order.repository.MemberOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        memberOrderRepository.saveMemberOrder(memberOrder);
    }

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
            memberOrderDetail.setItemName(memberOrderDetailAddDTO.getItemName());
            memberOrderDetail.setItemPrice(memberOrderDetailAddDTO.getItemPrice());
            memberOrderDetailList.add(memberOrderDetail);
        }
        for (MemberOrderDetail memberOrderDetail: memberOrderDetailList) {
            memberOrderRepository.saveMemberOrderDetail(memberOrderDetail);
        }
        MemberOrder memberOrder = memberOrderRepository.findMemberOrderByNo(maxMemberOrderNo);
        memberOrder.setMemberOrderDetailList(memberOrderDetailList);
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

    /* user */
    @Transactional(readOnly = true)
    public MemberOrderPageForm setMemberOrderListPage(int page, MemberOrderViewForm memberOrderViewForm) {

        int memberOrdersPerPage = 10;
        int pageLimit = 5;

        int itemCount = memberOrderRepository.findAllMemberOrdersByNo(memberOrderViewForm.getMemberNo()).size();

        int totalPage = (int) (Math.ceil((double) itemCount / memberOrdersPerPage));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        return new MemberOrderPageForm(page, totalPage, startPage, endPage);
    }


    /* user */
    @Transactional(readOnly = true)
    public List<MemberOrderDTO> findMemberOrderList(int page, MemberOrderViewForm memberOrderViewForm) {

        int memberOrdersPerPage = 10;
        int startPage = (page - 1) * memberOrdersPerPage;

        memberOrderViewForm.setStartPage(startPage);
        memberOrderViewForm.setMemberOrdersPerPage(memberOrdersPerPage);

        List<MemberOrder> memberOrderList = memberOrderRepository.findMemberOrderList(memberOrderViewForm);
        List<MemberOrderDTO> memberOrderDTOList = new ArrayList<>();
        for (MemberOrder memberOrder: memberOrderList) {
            memberOrderDTOList.add(MemberOrderDTO.toMemberOrderDTO(memberOrder));
        }
        return memberOrderDTOList;
    }
}
