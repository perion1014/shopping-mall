package com.example.shoppingmall.cart.controller;

import com.example.shoppingmall.cart.dto.CartReadForm_CMS;
import com.example.shoppingmall.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController_CMS {

    private final CartService cartService;
    private ArrayList<CartReadForm_CMS> cartList;

    @GetMapping("/{memberNo}")
    public String showMemberCartList(@PathVariable Long memberNo){



    return "/carts/member-cart-list";
    }


}
