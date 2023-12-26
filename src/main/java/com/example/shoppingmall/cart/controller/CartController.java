package com.example.shoppingmall.cart.controller;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.member.domain.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/{memberNo}")
    public String addItemsToMemberCart(@PathVariable(name = "memberNo") Long memberNo,
                                       @RequestParam(name = "itemNo") Long itemNo,
                                       @RequestParam(name = "cartAddItemSize") String cartAddItemSize,
                                       @RequestParam(name = "addCartItemQuantiy") Integer addCartItemQuantiy){

        cartService.addCartItem(memberNo, itemNo, cartAddItemSize, addCartItemQuantiy);
        //return "redirect:/carts/" + memberNo;
        return "redirect:/carts/carts/item_info_temp";
    }

    @PostMapping("/{memberNo}/{cartNo}/update")
    public String updateItemsInMemberCart(@RequestBody Map<String, String> jsonData, Model model, RedirectAttributes rttr){
        Integer currentQuantityNum = Integer.parseInt(jsonData.get("currentQuantityNum"));
        Long cartNo = Long.parseLong(jsonData.get("cartNo"));
        cartService.updateCartItem(cartNo, currentQuantityNum);
        return "carts/member-cart-list";
    }

    @PostMapping("/{memberNo}/{cartNo}/delete")
    public String deleteItemsInMemberCart(@RequestBody Map<String, String> jsonData, Model model){
        Long cartNo = Long.parseLong(jsonData.get("cartNo"));
        cartService.deleteCartItem(cartNo);
        return "carts/member-cart-list";
    }

    @GetMapping("/carts/item_info_temp")
    public String gotoItemInfo(){
        return "carts/itemInfo_Temp_CMS";
    }

}
