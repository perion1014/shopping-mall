package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.service.MemberOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderControllerPYM {

    private final MemberOrderService memberOrderService;
    private final CartService cartService;

    @GetMapping("/{memberNo}/orders/create")
    public String goToMemberOrderPage(@PathVariable(name="memberNo") Long memberNo,
                                      Model model,
                                      HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        Long loginMemberNo = loginMember.getMemberNo();;
        List<CartReadDTO> cartDTOList = cartService.getCartList(loginMemberNo);

        model.addAttribute("cartDTOList", cartDTOList);
        return "carts/member-cart-list";
    }

    @GetMapping("/order/member/test")
    public String goToMemberOrderTest() {
        return "/orders/member-order";
    }

    @GetMapping("/order/nonmember/test")
    public String goToNonmemberOrderTest() {
        return "/orders/nonmember-order";
    }

}
