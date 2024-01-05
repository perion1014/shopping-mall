package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.item.service.ItemService_CMS;
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
    private final ItemService_CMS itemService_cms;
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
        Map<String, Object> responseData = new HashMap<>();
        List<ItemStockDTO> stockCheckFalseList = new ArrayList<>();
        String SendResponseString = "";

        for(int i = 0; i < jsonData.size(); i++){
            ItemStockDTO itemStockDTO = new ItemStockDTO();
            itemStockDTO.setItemNo(jsonData.get(i).getItemNo());
            itemStockDTO.setItemSize(jsonData.get(i).getItemSize());

            if(!itemService_cms.CompareStockValuesFromCartAndDB(itemStockDTO, jsonData.get(i).getItemQuantity())){
                SendResponseString += jsonData.get(i).getItemName() + " : " + jsonData.get(i).getItemSize() + "사이즈" + ",";
            }

//            System.out.println(jsonData.get(i).getItemNo());
//            System.out.println(jsonData.get(i).getItemName());
//            System.out.println(jsonData.get(i).getItemSize());
//            System.out.println(jsonData.get(i).getItemQuantity());
        }

        if(SendResponseString.isEmpty()) {
            responseData.put("response", "데이터 전달/처리 성공");
        } else{
            responseData.put("response", SendResponseString);
        }

        return responseData;
    }

    @GetMapping("/orders/non-members")
    public String goToNonMemberOrderPage(){
        return "orders/nonmember-order-check";
    }

    @GetMapping("/temp")
    public String gototest(){
        return "orders/nonmember-order";
    }

}
