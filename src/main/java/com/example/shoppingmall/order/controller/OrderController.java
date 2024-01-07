package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.item.service.ItemService_CMS;
import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.dto.*;
import com.example.shoppingmall.order.service.MemberOrderService;
import com.example.shoppingmall.order.service.NonMemberOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final NonMemberOrderService nonMemberOrderService;

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
    public Map<String, Object> checkNonMemberOrderItemStock(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                                            HttpServletRequest req){

        System.out.println("컨트롤러 도착 확인");
        Map<String, Object> responseData = new HashMap<>();
        List<ItemStockDTO> stockCheckFalseList = new ArrayList<>();
        String sendResponseString = "";

        for(int i = 0; i < jsonData.size(); i++){
            ItemStockDTO itemStockDTO = new ItemStockDTO();
            itemStockDTO.setItemNo(jsonData.get(i).getItemNo());
            itemStockDTO.setItemSize(jsonData.get(i).getItemSize());

            if(!itemService_cms.CompareStockValuesFromCartAndDB(itemStockDTO, jsonData.get(i).getItemQuantity())){
                sendResponseString += jsonData.get(i).getItemName() + " : " + jsonData.get(i).getItemSize() + "사이즈" + ",";
            }

//            System.out.println(jsonData.get(i).getItemNo());
//            System.out.println(jsonData.get(i).getItemName());
//            System.out.println(jsonData.get(i).getItemSize());
//            System.out.println(jsonData.get(i).getItemQuantity());
        }

        if(sendResponseString.isEmpty()) {
            List<NonMemberOrderDetailAddDTO> nonMemberOrderDetailAddDTOList = new ArrayList<>();
            NonMemberOrderDetailAddDTO nonMemberOrderDetailAddDTO = null;

            for(int i = 0; i < jsonData.size(); i++){
                nonMemberOrderDetailAddDTO = new NonMemberOrderDetailAddDTO();
                nonMemberOrderDetailAddDTO.setItemNo(jsonData.get(i).getItemNo());
                nonMemberOrderDetailAddDTO.setItemName(jsonData.get(i).getItemName());
                nonMemberOrderDetailAddDTO.setItemSize(jsonData.get(i).getItemSize());
                nonMemberOrderDetailAddDTO.setItemQuantity(jsonData.get(i).getItemQuantity());
                nonMemberOrderDetailAddDTO.setItemPrice(jsonData.get(i).getItemPrice());
                nonMemberOrderDetailAddDTOList.add(nonMemberOrderDetailAddDTO);
            }

            HttpSession session = req.getSession();
            session.setAttribute("nonMemberOrderDetailAddDTOList", nonMemberOrderDetailAddDTOList);
            responseData.put("response", "데이터 전달/처리 성공");
        } else{
            responseData.put("response", sendResponseString);
        }

        return responseData;
    }

    @GetMapping("/orders/non-members")
    public String goToNonMemberOrderPage(){
        return "orders/nonmember-order-check";
    }

    @PostMapping("/orders/check-itemstock")
    @ResponseBody
    public Map<String, Object> checkNonMemberOrderItemStock(@RequestBody Map<String, String> jsonData){

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("response", "데이터 전달 성공");

        return responseData;
    }

    @GetMapping("/orders/create")
    public String goToInputNonMemberOrderPage(HttpServletRequest req){

//        HttpSession session = req.getSession();
//        List<NonMemberOrderDetailAddDTO> nonMemberOrderDetailAddDTOList = (List<NonMemberOrderDetailAddDTO>) session.getAttribute("nonMemberOrderDetailAddDTOList");
//        System.out.println("세션으로 받아온 리스트 사이즈 : " + nonMemberOrderDetailAddDTOList.size());
//
//        for(int i = 0; i < nonMemberOrderDetailAddDTOList.size(); i++){
//            System.out.println(nonMemberOrderDetailAddDTOList.get(i).getItemNo());
//            System.out.println(nonMemberOrderDetailAddDTOList.get(i).getItemName());
//            System.out.println(nonMemberOrderDetailAddDTOList.get(i).getItemSize());
//            System.out.println(nonMemberOrderDetailAddDTOList.get(i).getItemQuantity());
//            System.out.println(nonMemberOrderDetailAddDTOList.get(i).getItemPrice());
//        }

        return "orders/nonmember-order";
    }

    @PostMapping("/orders/create")
    public String makeNonMemberOrder(@ModelAttribute NonMemberOrderAddDTO nonMemberOrderAddDTO,
                                     HttpServletRequest req){

        System.out.println("입력받은 주문자명 : " + nonMemberOrderAddDTO.getNonMemberName());
        System.out.println("입력받은 휴대폰 번호 : " + nonMemberOrderAddDTO.getOrderHp());
        System.out.println("입력받은 이메일 : " + nonMemberOrderAddDTO.getOrderEmail());
        System.out.println("입력받은 수령자명 : " + nonMemberOrderAddDTO.getReceiverName());
        System.out.println("입력받은 우편번호 : " + nonMemberOrderAddDTO.getOrderPostalCode());
        System.out.println("입력받은 기본주소 : " + nonMemberOrderAddDTO.getOrderAddressBasic());
        System.out.println("입력받은 상세주소 : " + nonMemberOrderAddDTO.getOrderAddressDetail());

        nonMemberOrderService.saveNonMemberOrder(nonMemberOrderAddDTO);
        List<NonMemberOrderDetailAddDTO> nonMemberOrderDetailAddDTOList = (List<NonMemberOrderDetailAddDTO>) req.getSession().getAttribute("nonMemberOrderDetailAddDTOList");
        nonMemberOrderService.saveNonMemberOrderDetail(nonMemberOrderDetailAddDTOList);

        req.getSession().setAttribute("nonMemberOrderDetailAddDTOList", null);

        return "orders/nonmember-order-success-test";
    }

    @PostMapping("/orders/non-members")
    @ResponseBody
    public String showNonMemberOrderList(@RequestBody Map<String, String> jsonData){

        return "orders/nonmember-order-detail-check-test";
    }

}
