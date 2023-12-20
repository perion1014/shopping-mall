package com.example.shoppingmall.cart.controller;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private ArrayList<CartReadDTO> cartList;

    @GetMapping("/{memberNo}")
    public String showMemberCartList(@PathVariable(name = "memberNo") Long memberNo, Model model) {
        List<CartReadDTO> cartDTOList = cartService.getCartList(memberNo);
        model.addAttribute("cartDTOList", cartDTOList);
        return "carts/member-cart-list";
    }

    @PostMapping("/{memberNo}/{cartNo}/update")
    public String updateItemsInMemberCart(@RequestBody Map<String, String> jsonData){
        Integer currentQuantityNum = Integer.parseInt(jsonData.get("currentQuantityNum"));
        Integer cartNo = Integer.parseInt(jsonData.get("cartNo"));
        Integer memberNo = Integer.parseInt(jsonData.get("memberNo"));
        System.out.println("json으로 넘겨받은 회원 번호 : " + memberNo);
        System.out.println("json으로 넘겨받은 장바구니 번호 : " + cartNo);
        System.out.println("json으로 넘겨받은 아이템 변경 수량 : " + currentQuantityNum);
        return "redirect:/carts/" + memberNo;
    }
}
