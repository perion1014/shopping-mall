package com.example.shoppingmall.order.service;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.dto.*;
import com.example.shoppingmall.order.form.MemberOrderAdminViewForm;
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
//    public MemberOrderDetailDTO findMemberOrderDetail(Long memberOrderNo) {
//        MemberOrderDetail memberOrderDetail = memberOrderRepository.findMemberOrderDetail(memberOrderNo);
//        MemberOrderDetailDTO memberOrderDetailDTO = MemberOrderDetailDTO.toMemberOrderDetailDTO(memberOrderDetail);
//        return memberOrderDetailDTO;
//    }



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
    public List<MemberOrderDTO> getMemberOrderListPage(int page, MemberOrderViewForm memberOrderViewForm) {

        int memberOrdersPerPage = 10;
        int startPage = (page - 1) * memberOrdersPerPage;

        memberOrderViewForm.setStartPage(startPage);
        memberOrderViewForm.setMemberOrdersPerPage(memberOrdersPerPage);

        List<MemberOrder> memberOrderList = memberOrderRepository.findMemberOrderList(memberOrderViewForm);
        List<MemberOrderDTO> memberOrderDTOList = new ArrayList<>();
        for (MemberOrder memberOrder: memberOrderList) {
            Long memberOrderNo = memberOrder.getMemberOrderNo();
            List<MemberOrderDetail> memberOrderDetailList = memberOrderRepository.findMemberOrderDetail(memberOrderNo);
            memberOrder.setMemberOrderDetailList(memberOrderDetailList);
            memberOrderDTOList.add(MemberOrderDTO.toMemberOrderDTO(memberOrder));
        }
        return memberOrderDTOList;
    }

    public void cancelMemberOrder(Long memberOrderNo) {
        memberOrderRepository.cancelMemberOrder(memberOrderNo);
    }

    public MemberOrderDTO findMemberOrderByNo(Long memberOrderNo) {
        MemberOrder memberOrder = memberOrderRepository.findMemberOrderByNo(memberOrderNo);
        List<MemberOrderDetail> memberOrderDetailList = memberOrderRepository.findMemberOrderDetail(memberOrderNo);
        memberOrder.setMemberOrderDetailList(memberOrderDetailList);
        return MemberOrderDTO.toMemberOrderDTO(memberOrder);
    }


    /* admin */
    @Transactional(readOnly = true)
    public MemberOrderPageForm setMemberOrderAdminListPage(int page) {

        int memberOrdersPerPage = 10;
        int pageLimit = 5;

        int itemCount = memberOrderRepository.getMemberOrderList().size();

        int totalPage = (int) (Math.ceil((double) itemCount / memberOrdersPerPage));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        return new MemberOrderPageForm(page, totalPage, startPage, endPage);
    }

    /* admin */
    @Transactional(readOnly = true)
    public List<MemberOrderAdminViewDTO> getMemberOrderAdminListPage(int page, MemberOrderAdminViewForm memberOrderAdminViewForm) {

        int memberOrdersPerPage = 10;
        int startPage = (page - 1) * memberOrdersPerPage;

        memberOrderAdminViewForm.setStartPage(startPage);
        memberOrderAdminViewForm.setMemberOrdersPerPage(memberOrdersPerPage);

        List<MemberOrder> memberOrderList = memberOrderRepository.findMemberOrderAdminList(memberOrderAdminViewForm);
        List<MemberOrderAdminViewDTO> memberOrderAdminViewDTOList = new ArrayList<>();
        for (MemberOrder memberOrder: memberOrderList) {
            MemberOrderAdminViewDTO memberOrderAdminViewDTO = new MemberOrderAdminViewDTO();
            Long memberOrderNo = memberOrder.getMemberOrderNo();
            ///////////////////////////////////////
            memberOrderAdminViewDTO.setMemberOrderNo(memberOrderNo);
            memberOrderAdminViewDTO.setMemberNo(memberOrder.getMemberNo());
            memberOrderAdminViewDTO.setReceiverName(memberOrder.getReceiverName());
            memberOrderAdminViewDTO.setOrderHp(memberOrder.getOrderHp());
            memberOrderAdminViewDTO.setOrderPostalCode(memberOrder.getOrderPostalCode());
            memberOrderAdminViewDTO.setOrderAddressBasic(memberOrder.getOrderAddressBasic());
            memberOrderAdminViewDTO.setOrderStatus(memberOrder.getOrderStatus());
            ///////////////////////////////////////
            List<MemberOrderDetail> memberOrderDetailList = memberOrderRepository.findMemberOrderDetail(memberOrderNo);
            memberOrder.setMemberOrderDetailList(memberOrderDetailList);
            memberOrderAdminViewDTOList.add(MemberOrderAdminViewDTO.toMemberOrderAdminViewDTO(memberOrder));
        }
        return memberOrderAdminViewDTOList;
    }
}
