package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.dto.MemberOrderAddDTO;
import com.example.shoppingmall.order.dto.MemberOrderDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailDTO;
import com.example.shoppingmall.order.service.MemberOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderControllerPYM {

    private final MemberOrderService memberOrderService;
    private final CartService cartService;

    /* user */
    @PostMapping("/members/{memberNo}/orders/create")
    public String goToMemberOrderPage(@PathVariable(name="memberNo") Long memberNo,
                                      @ModelAttribute CartReadDTO cartReadDTO,
                                      Model model,
                                      HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }

        List<CartReadDTO> cartDTOList = null;

        model.addAttribute("cartDTOList", cartDTOList);
        return "orders/member-order-pym";
    }

    /* user */
    @PostMapping("/members/{memberNo}/orders/create-order")
    public String makeMemberOrder(@PathVariable(name="memberNo") Long memberNo,
                                  @ModelAttribute MemberOrderAddDTO memberOrderAddDTO) {
        memberOrderAddDTO.setMemberNo(memberNo);
        memberOrderService.saveMemberOrder(memberOrderAddDTO);
        return null;
    }

    /* user */
    @GetMapping("/members/{memberNo}/orders")
    public String showMemberOrderList(@PathVariable(name="memberNo") Long memberNo,
                                      HttpServletRequest request,
                                      Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null ) {
            return "redirect:/members/login";
        }

        List<MemberOrderDTO> memberOrderDTOList = memberOrderService.findMemberOrderList(memberNo);
        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
        return null;
    }

    /* user */
    @GetMapping("/members/{memberNo}/orders/{orderNo}")
    public String showMemberOrderDetail(@PathVariable(name="memberNo") Long memberNo,
                                        @PathVariable(name="orderNo") Long orderNo,
                                        HttpServletRequest request,
                                        Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null ) {
            return "redirect:/members/login";
        }
        MemberOrderDetailDTO memberOrderDetailDTO = memberOrderService.findMemberOrderDetail(orderNo);
        model.addAttribute("memberOrderDetailDTO", memberOrderDetailDTO);
        return null;
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
