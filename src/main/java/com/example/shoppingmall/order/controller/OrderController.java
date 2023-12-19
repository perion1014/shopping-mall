package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.dto.MemberOrderAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDTO;
import com.example.shoppingmall.order.service.MemberOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String makeMemberOrder(@PathVariable(name="memberNo") Long memberNo, @ModelAttribute MemberOrderAddDTO memberOrderAddDTO) {
        memberOrderService.saveMemberOrder(memberOrderAddDTO);
        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }

    @GetMapping("/admin/members")
    public String showMemberOrderList() {
        return "admins/admins-order";
    }

    @PostMapping("/admin/members/{orderNo}")
    public String showMemberOrderDetail(@PathVariable(name="orderNo") Long orderNo, @ModelAttribute MemberOrderDTO memberOrderDTO) {

        return null;
    }

}
