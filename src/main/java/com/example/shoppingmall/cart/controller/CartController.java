package com.example.shoppingmall.cart.controller;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private ArrayList<CartReadDTO> cartList;

    @GetMapping("/")
    public String test(){
        System.out.println("http://localhost:8082/carts 진입 성공");
        return "/carts/member-cart-list";
    }

    @GetMapping("/{memberNo}")
    public String showMemberCartList(@PathVariable Long memberNo) {
        System.out.println("/cart/memberNo 진입 성공");
        ArrayList<CartReadDTO> cartDTOList = cartService.getCartList(memberNo);
        return "/carts/member-cart-list";
    }
}
