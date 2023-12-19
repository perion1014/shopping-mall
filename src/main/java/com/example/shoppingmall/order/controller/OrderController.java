package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.order.service.MemberOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String makeMemberOrder(@PathVariable(name="memberNo") Long memberNo) {
        memberOrderService.saveMemberOrder(memberNo);
        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }


}
