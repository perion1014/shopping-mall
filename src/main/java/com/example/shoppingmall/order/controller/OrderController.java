package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.dto.*;
import com.example.shoppingmall.order.service.MemberOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final MemberOrderService memberOrderService;

//    @PostMapping("/members/{memberNo}/orders/check-itemstock")
//    @ResponseBody
//    public Map<String, Object> checkMemberOrderItemStock(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
//                                                         @PathVariable Integer memberNo){
//
////        System.out.println(jsonData);
////        System.out.println(memberNo);
//        System.out.println("컨트롤러에 도착 확인");
//
//        for(int i =0; i <jsonData.size(); i++){
//            System.out.println(jsonData.get(i).getItemName());
//            System.out.println(jsonData.get(i).getItemSize());
//            System.out.println(jsonData.get(i).getItemQuantity());
//        }
//
//        Map<String, Object> responseData = new HashMap<>();
//        responseData.put("response", "선택하신 상품의 재고가 없습니다.");
//
//        return responseData;
//    }

    @PostMapping("orders/check-itemstock")
    @ResponseBody
    public Map<String, Object> checkNonMemberOrderItemStock(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData){

        System.out.println("컨트롤러 도착 확인");

        for(int i = 0; i < jsonData.size(); i++){
            System.out.println(jsonData.get(i).getItemName());
            System.out.println(jsonData.get(i).getItemSize());
            System.out.println(jsonData.get(i).getItemQuantity());
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("response", "데이터 전달 후 응답 확인");

        return responseData;
    }

    @GetMapping("/orders/non-members")
    public String goToNonMemberOrderPage(){
        return "orders/nonmember-order-check";
    }

}
