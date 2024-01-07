package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.item.service.ItemService_CMS;
import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.dto.*;
import com.example.shoppingmall.order.service.MemberOrderService;
import com.example.shoppingmall.order.service.NonMemberOrderService;
import com.example.shoppingmall.order.validation.OrderValidationSequence;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ItemService_CMS itemService_cms;
    private final ItemService itemService;
    private final MemberOrderService memberOrderService;
    private final NonMemberOrderService nonMemberOrderService;
    private final CartService cartService;

    //비회원 - 장바구니 목록에서 구매 누를 때 재고 체크하고
    //시간되면 코드 Service로 빼볼 예정
    @PostMapping("/orders/check-itemstock")
    @ResponseBody
    public Map<String, Object> checkNonMemberOrderItemStock(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                                            HttpServletRequest req){

        HttpSession session = req.getSession();
        Map<String, Object> responseData = new HashMap<>();
        List<ItemStockDTO> stockCheckFalseList = new ArrayList<>();
        Boolean isPurchaseInItemDetailPage = jsonData.get(0).getIsPurchaseInItemDetailPage();
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
//            System.out.println(jsonData.get(i).getItemPrice());
        }
        //장바구니에서 항목선택해서 구매 눌렀을 때 - 구매 페이지로 이동
        if(isPurchaseInItemDetailPage == null){

            if(sendResponseString.isEmpty()) {
                List<NonMemberOrderDetailAddDTO> nonMemberOrderDetailAddDTOList = new ArrayList<>();
                NonMemberOrderDetailAddDTO nonMemberOrderDetailAddDTO = null;

                for(int i = 0; i < jsonData.size(); i++){
                    nonMemberOrderDetailAddDTO = new NonMemberOrderDetailAddDTO();
                    nonMemberOrderDetailAddDTO.setItemNo(jsonData.get(i).getItemNo());
    //                System.out.println("구매하기 누른 후 세션에 들어갈 상품 No : " + nonMemberOrderDetailAddDTO.getItemNo());
                    nonMemberOrderDetailAddDTO.setItemName(jsonData.get(i).getItemName());
    //                System.out.println("구매하기 누른 후 세션에 들어갈 상품 이름 : " + nonMemberOrderDetailAddDTO.getItemName());
                    nonMemberOrderDetailAddDTO.setItemSize(jsonData.get(i).getItemSize());
    //                System.out.println("구매하기 누른 후 세션에 들어갈 상품 사이즈 : " + nonMemberOrderDetailAddDTO.getItemSize());
                    nonMemberOrderDetailAddDTO.setItemQuantity(jsonData.get(i).getItemQuantity());
    //                System.out.println("구매하기 누른 후 세션에 들어갈 상품 수량 : " + nonMemberOrderDetailAddDTO.getItemQuantity());
                    nonMemberOrderDetailAddDTO.setItemPrice(jsonData.get(i).getItemPrice());
    //                System.out.println("구매하기 누른 후 세션에 들어갈 상품 가격 : " + nonMemberOrderDetailAddDTO.getItemPrice());
                    nonMemberOrderDetailAddDTOList.add(nonMemberOrderDetailAddDTO);
                }

                session.setAttribute("nonMemberOrderDetailAddDTOList", nonMemberOrderDetailAddDTOList);
            }

        }
        //상품 상세에서 구매 눌렀을 때 - 비회원 장바구니 목록 세션에 넘어온 값만 추가
        else{
            if(sendResponseString.isEmpty() == true){
                session.setAttribute("nonmemberCartList", cartService.nonMemberAddCartItem(
                        itemService.getItemThumbByNo(jsonData.get(0).getItemNo()),
                        jsonData.get(0).getItemName(),
                        jsonData.get(0).getItemSize(),
                        jsonData.get(0).getItemPrice(),
                        jsonData.get(0).getItemQuantity(),
                        jsonData.get(0).getItemNo(),
                        req));
            }
        }

        if(sendResponseString.isEmpty()){
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

    //비회원 - 재고 체크 후 주문 입력 페이지로 이동
    @GetMapping("/orders/create")
    public String goToInputNonMemberOrderPage(@ModelAttribute("nonMemberOrderAddDTO") NonMemberOrderAddDTO nonMemberOrderAddDTO){
        return "orders/nonmember-order";
    }

    //비회원 - 주문 입력 페이지에서 입력받은 후 주문+주문상세 DB에 추가
    /*PRG 패턴 반드시 써주세요 */
    @PostMapping("/orders/create")
    public String makeNonMemberOrder(@Validated(OrderValidationSequence.class)
            @ModelAttribute("nonMemberOrderAddDTO") NonMemberOrderAddDTO nonMemberOrderAddDTO,
                                     BindingResult bindingResult,
                                     HttpServletRequest req){

        if(bindingResult.hasErrors()){
            return "orders/nonmember-order";
        }

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

        return "redirect:/orders/nonmember-order-success-test";
    }

    //비회원 - 주문 정보 입력 후 해당 주문 상세 페이지로 이동
    @PostMapping("/orders/non-members")
    @ResponseBody
    public String showNonMemberOrderList(@RequestBody Map<String, String> jsonData){

        return "orders/nonmember-order-detail-check-test";
    }

}
