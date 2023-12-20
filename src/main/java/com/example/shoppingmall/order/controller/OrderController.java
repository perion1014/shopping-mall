package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.dto.MemberOrderAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailDTO;
import com.example.shoppingmall.order.service.MemberOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final MemberOrderService memberOrderService;

    @GetMapping("/{memberNo}/create")
    public String goToInputMemberOrder(@PathVariable(name="memberNo") Long memberNo) {
        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }

    @PostMapping("/{memberNo}/create")
    public String makeMemberOrder(@PathVariable(name="memberNo") Long memberNo, @ModelAttribute MemberOrderAddDTO memberOrderAddDTO, @ModelAttribute MemberOrderDetailAddDTO memberOrderDetailAddDTO) {
        memberOrderService.saveMemberOrder(memberOrderAddDTO);
        memberOrderService.saveMemberOrderDetail(memberOrderDetailAddDTO);
        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }

    @GetMapping("/{memberNo}/create-success")
    public String makeMemberOrderSuccess(@PathVariable(name="memberNo") Long memberNo) {
        return null;
    }

    @GetMapping("/admin/members")
    public String showMemberOrderList(Model model) {
        List<MemberOrderDTO> memberOrderDTOList = memberOrderService.getMemberOrderList();
        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
        return "admins/admins-order";    // html 파일이 생성되면 그때 수정할 예정.
    }

    @PostMapping("/admin/members/{orderNo}")
    public String showMemberOrderDetail(@PathVariable(name="orderNo") Long orderNo, Model model) {
        MemberOrderDetailDTO memberOrderDetailDTO = memberOrderService.getMemberOrderDetail();
        model.addAttribute("memberOrderDetailDTO", memberOrderDetailDTO);
        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }

}
