package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.cart.dto.CartDeleteDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.dto.ItemStockReduceDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.item.service.ItemService_CMS;
import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.dto.*;
import com.example.shoppingmall.order.form.MemberOrderAdminViewForm;
import com.example.shoppingmall.order.form.MemberOrderViewForm;
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
                List<Long> deleteNonmemberCartIndexList = new ArrayList<>();

                for(int i = 0; i < jsonData.size(); i++){
                    deleteNonmemberCartIndexList.add(jsonData.get(i).getNonMemberCartNo());
                    System.out.println("카트 번호 : " + jsonData.get(i).getNonMemberCartNo());
                    nonMemberOrderDetailAddDTO = new NonMemberOrderDetailAddDTO();
                    nonMemberOrderDetailAddDTO.setItemNo(jsonData.get(i).getItemNo());
                    nonMemberOrderDetailAddDTO.setItemName(jsonData.get(i).getItemName());
                    nonMemberOrderDetailAddDTO.setItemSize(jsonData.get(i).getItemSize());
                    nonMemberOrderDetailAddDTO.setItemQuantity(jsonData.get(i).getItemQuantity());
                    nonMemberOrderDetailAddDTO.setItemPrice(jsonData.get(i).getItemPrice());
                    nonMemberOrderDetailAddDTOList.add(nonMemberOrderDetailAddDTO);
                }

                session.setAttribute("deleteNonmemberCartIndexList", deleteNonmemberCartIndexList);
                session.setAttribute("nonMemberOrderDetailAddDTOList", nonMemberOrderDetailAddDTOList);
            }

        }
        //상품 상세에서 구매 눌렀을 때 - 비회원 장바구니 목록 세션에 넘어온 값만 추가하고 장바구니 목록으로 이동
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

    //비회원 - 주문 조회를 위한 정보 입력 페이지
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
                                     HttpServletRequest req,
                                     Model model){

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
        nonMemberOrderService.saveNonMemberOrderDetail(nonMemberOrderDetailAddDTOList, req);
        nonMemberOrderService.setNowOrderNo(req.getSession());
        nonMemberOrderService.deleteOrderedCarts(req.getSession());

        return "redirect:/orders/create-success";
    }

    //비회원 - 주문 완료 시, 주문 성공 페이지로 이동
    @GetMapping("/orders/create-success")
    public String goToMakeNonMemberOrderSuccess(){
        return "orders/nonmember-order-success-test";
    }

    //비회원 - 주문 정보 입력 후 해당 주문 상세 페이지로 이동
    @PostMapping("/orders/non-members")
    @ResponseBody
    public Map<String, Object> showNonMemberOrderList(@RequestBody nonMemberOrderCheckDTO jsonData){

        Map<String, Object> responseData = new HashMap<>();

        System.out.println("입력받은 주문 번호 : " + jsonData.getCheckNonMemberOrderNo());
        System.out.println("입력받은 주문자 명 : " + jsonData.getCheckNonMemberOrderName());

        nonMemberOrderService.getNonmemberOrderDetailFromOrderNoAndOrderName(jsonData.getCheckNonMemberOrderNo(), jsonData.getCheckNonMemberOrderName());

        responseData.put("response", "데이터 수신 성공");

        return responseData;

    }

    //비회원 - 비회원 주문 상세 페이지로 이동
    @GetMapping("/orders/{orderNo}")
    public String showNonMemberOrderDetail(){
        return "orders/nonmember-order-detail-check-test";
    }

    //====================================================영무님 메소드

        /* 1 */
        /* user */
        @PostMapping("/members/{memberNo}/orders/check-itemstock")
        @ResponseBody
        public Map<String, Object> checkMemberOrderItemStock(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                                             @PathVariable(name="memberNo") Integer memberNo){

            boolean isStockEnough = true;   // 재고가 충분한지? -> default value: true

            for (int i = 0; i < jsonData.size(); i++) {
                ItemStockDTO itemStockDTO = new ItemStockDTO();
                itemStockDTO.setItemNo(jsonData.get(i).getItemNo());
                itemStockDTO.setItemSize(jsonData.get(i).getItemSize());
                Integer itemStockValue = itemService.getItemStockValueByItemNoAndItemSize(itemStockDTO);    // 해당 상품의 재고
                if ( jsonData.get(i).getItemQuantity() > itemStockValue ) { // 선택한 수량이 재고보다 더 많으면
                    isStockEnough = false;                                  // 충분하지 않음!
                }
            }

            Map<String, Object> responseData = new HashMap<>();

            if (isStockEnough == false) {   // 재고가 더 적은 cart 객체가 하나라도 있을 경우
                responseData.put("response", "선택하신 상품의 재고가 없습니다.");
            } else {                // 모든 cart 장바구니 객체에 대하여 재고가 충분할 경우
                responseData.put("response", jsonData);
            }
            return responseData;
        }

        /* 2 */
        /* user */
        @PostMapping("/members/{memberNo}/orders/create")
        public String goToMemberOrderPage(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                          @PathVariable(name="memberNo") Long memberNo,
                                          HttpServletRequest request) {

            HttpSession session = request.getSession();
            if (session.getAttribute("loginMember") == null) {
                return "redirect:/member/login";
            }

            List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList = new ArrayList<>();
            for (int i = 0; i < jsonData.size(); i++) {
                MemberOrderDetailAddDTO memberOrderDetailAddDTO = new MemberOrderDetailAddDTO();
                memberOrderDetailAddDTO.setItemNo(jsonData.get(i).getItemNo());
                memberOrderDetailAddDTO.setItemName(jsonData.get(i).getItemName());
                memberOrderDetailAddDTO.setItemSize(jsonData.get(i).getItemSize());
                memberOrderDetailAddDTO.setItemQuantity(jsonData.get(i).getItemQuantity());
                memberOrderDetailAddDTO.setItemPrice(jsonData.get(i).getItemPrice());
                memberOrderDetailAddDTOList.add(memberOrderDetailAddDTO);
            }

            for (MemberOrderDetailAddDTO memberOrderDetailAddDTO: memberOrderDetailAddDTOList) {
                memberOrderDetailAddDTO.setItemThumb(itemService.getItemThumbByNo(memberOrderDetailAddDTO.getItemNo()));
            }
            session.setAttribute("memberOrderDetailAddDTOList", memberOrderDetailAddDTOList);

            Member member = (Member) session.getAttribute("loginMember");
            MemberOrderAddDTO memberOrderAddDTO = new MemberOrderAddDTO();
            memberOrderAddDTO.setMemberNo(memberNo);
            memberOrderAddDTO.setOrderHp(member.getMemberHp());
            memberOrderAddDTO.setOrderPostalCode(member.getMemberPostalCode());
            memberOrderAddDTO.setOrderAddressBasic(member.getMemberAddressBasic());
            memberOrderAddDTO.setOrderAddressDetail(member.getMemberAddressDetail());
            memberOrderAddDTO.setReceiverName(member.getMemberName());
            memberOrderAddDTO.setMemberOrderDetailAddDTOList(memberOrderDetailAddDTOList);

            session.setAttribute("memberOrderDTO", memberOrderAddDTO);

            return "orders/member-order";
        }

        @GetMapping("/members/orders/create")
        public String tempMethod(@ModelAttribute("memberOrderAddDTO") MemberOrderAddDTO memberOrderAddDTO) {
            return "orders/member-order";
        }

        /* 3 */
        /* user */

        /**
         * PRG 패턴 써주세요!
         */
        @PostMapping("/members/{memberNo}/orders/create-success")
        public String makeMemberOrderSuccess(@PathVariable(name="memberNo") Long memberNo,
                                             @Validated(OrderValidationSequence.class)
                                             @ModelAttribute("memberOrderAddDTO") MemberOrderAddDTO memberOrderAddDTO,
                                             BindingResult bindingResult,
                                             HttpServletRequest request,
                                             Model model) {

            if(bindingResult.hasErrors()){
                return "orders/member-order";
            }

            HttpSession session = request.getSession();

            MemberOrderAddDTO memberOrderDTO = (MemberOrderAddDTO) session.getAttribute("memberOrderDTO");
            memberOrderDTO.setOrderAddressBasic(memberOrderAddDTO.getOrderAddressBasic());
            memberOrderDTO.setOrderAddressDetail(memberOrderAddDTO.getOrderAddressDetail());
            List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList = memberOrderDTO.getMemberOrderDetailAddDTOList();

            memberOrderService.saveMemberOrder(memberNo, memberOrderDTO);
            Long maxMemberOrderNo = memberOrderService.getMaxMemberOrderNo();
            memberOrderService.saveMemberOrderDetail(maxMemberOrderNo, memberOrderDetailAddDTOList);
            model.addAttribute("memberOrderNo", maxMemberOrderNo);

            for (MemberOrderDetailAddDTO memberOrderDetailAddDTO: memberOrderDetailAddDTOList) {
                ItemStockReduceDTO itemStockReduceDTO = new ItemStockReduceDTO(memberOrderDetailAddDTO.getItemNo(), memberOrderDetailAddDTO.getItemSize(), memberOrderDetailAddDTO.getItemQuantity());
                itemService.reduceItemStocks(itemStockReduceDTO);
                CartDeleteDTO cartDeleteDTO = new CartDeleteDTO(memberNo, memberOrderDetailAddDTO.getItemNo(), memberOrderDetailAddDTO.getItemSize());
                cartService.deleteCartItemByItemNoAndItemSize(cartDeleteDTO);
            }
            session.setAttribute("memberOrderDTO", null);
            session.setAttribute("memberOrderDetailAddDTOList", null);

            return "orders/member-order-success";
        }


        /* user */
        @GetMapping("/members/{memberNo}/orders")
        public String showMemberOrderList(@PathVariable(name="memberNo") Long memberNo,
                                          @RequestParam(value="page", required=false, defaultValue="1") int page,
                                          @ModelAttribute MemberOrderViewForm memberOrderViewForm,
                                          HttpServletRequest request,
                                          Model model) {

            HttpSession session = request.getSession();
            if (session.getAttribute("loginMember") == null ) {
                return "redirect:/members/login";
            }
            memberOrderViewForm.setMemberNo(memberNo);
            model.addAttribute("pageSettings", memberOrderService.setMemberOrderListPage(page, memberOrderViewForm));
            List<MemberOrderDTO> memberOrderDTOList = memberOrderService.getMemberOrderListPage(page, memberOrderViewForm);
            List<Integer> priceSumList = new ArrayList<>();
            for (MemberOrderDTO memberOrderDTO: memberOrderDTOList) {
                Integer priceSum = 0;
                for (int i = 0; i < memberOrderDTO.getMemberOrderDetailDTOList().size(); i++) {
                    priceSum += memberOrderDTO.getMemberOrderDetailDTOList().get(i).getItemPrice() * memberOrderDTO.getMemberOrderDetailDTOList().get(i).getItemQuantity();
                }
                priceSumList.add(priceSum);
            }
            for (int i = 0; i < priceSumList.size(); i++) {
                memberOrderDTOList.get(i).setPriceSum(priceSumList.get(i));
            }
            model.addAttribute("memberOrderDTOList", memberOrderDTOList);
            return "orders/member-order-list";
        }

        /* user */
        @PostMapping("/members/{memberNo}/orders")
        public String cancelMemberOrder(@PathVariable(name = "memberNo") Long memberNo,
                                        @RequestParam(name = "orderNo") Long orderNo) {
            memberOrderService.cancelMemberOrder(orderNo);
            return "redirect:/members/" + memberNo + "/orders";
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
            MemberOrderDTO memberOrderDTO = memberOrderService.findMemberOrderByNo(orderNo);
            for (MemberOrderDetailDTO memberOrderDetailDTO: memberOrderDTO.getMemberOrderDetailDTOList()) {
                String itemThumb = itemService.getItemThumbByNo(memberOrderDetailDTO.getItemNo());
                memberOrderDetailDTO.setItemThumb(itemThumb);
            }
            model.addAttribute("memberOrderDTO", memberOrderDTO);
            return "orders/member-order-detail";
        }

        //..
        @GetMapping("/order/member/test")
        public String goToMemberOrderTest() {
            return "/orders/member-order";
        }

        //..
        @GetMapping("/order/nonmember/test")
        public String goToNonmemberOrderTest() {
            return "/orders/nonmember-order";
        }

        /* admin */
        @GetMapping("/orders/admin/members")
        public String showMemberOrderList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                          @RequestParam(value = "searchCategory", defaultValue = "member_no") String searchCategory,
                                          @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                                          HttpServletRequest request,
                                          Model model) {

            HttpSession session = request.getSession();
            session.setAttribute("adminSearchCategory", searchCategory);

            MemberOrderAdminViewForm memberOrderAdminViewForm = new MemberOrderAdminViewForm();

            memberOrderAdminViewForm.setSearchCategory(searchCategory);

            if ( searchKeyword == null || searchKeyword.replace("+", "").replace(" ", "").equals("")) {
                model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
                List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
                model.addAttribute("memberOrderDTOList", memberOrderDTOList);
            } else {
                if (searchCategory.equals("member_order_no") || searchCategory.equals("member_no")) {                           // Long 타입
                    try {
                        memberOrderAdminViewForm.setSearchKeywordLong(Long.parseLong(searchKeyword));
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                        return "admins/admins-order";
                    }
                    ////////////////////////////////////////////////////////////////////////////// 예외 처리 끝
                    if (searchCategory.equals("member_order_no")) {
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearchLong(page, memberOrderAdminViewForm));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearchLong(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    } else {
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearchLong2(page, memberOrderAdminViewForm));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearchLong2(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    }
                    ////////////////////////////////////////////////////////////////////////////// DTO 담기
                } else if (searchCategory.equals("order_postal_code")) {                                                        // Integer 타입
                    try {
                        memberOrderAdminViewForm.setSearchKeywordInteger(Integer.parseInt(searchKeyword));
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                        return "admins/admins-order";
                    }
                    ////////////////////////////////////////////////////////////////////////////// 예외 처리 끝
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearchInteger(page, memberOrderAdminViewForm));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearchInteger(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    ////////////////////////////////////////////////////////////////////////////// DTO 담기
                } else {                                                                                                        // String 타입
                    try {
                        memberOrderAdminViewForm.setSearchKeywordString(searchKeyword);
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                        return "admins/admins-order";
                    }
                    ////////////////////////////////////////////////////////////////////////////// 예외 처리 끝
                    if (searchCategory.equals("receiver_name")) {
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearch(page, memberOrderAdminViewForm));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearch(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    } else if (searchCategory.equals("order_hp")){
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearch2(page, memberOrderAdminViewForm));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearch2(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    } else {
                        model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearch3(page, memberOrderAdminViewForm));
                        List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearch3(page, memberOrderAdminViewForm);
                        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    }
                    ////////////////////////////////////////////////////////////////////////////// DTO 담기
                }
            }
            return "admins/admins-order";
        }

        /* admin */
        @GetMapping("/orders/admin/members/{orderNo}")
        public String showMemberOrderDetail(@PathVariable(name="orderNo") Long orderNo,
                                            Model model) {
            List<MemberOrderDetailDTO> memberOrderDetailDTOList = memberOrderService.getMemberOrderDetailList(orderNo);
            model.addAttribute("memberOrderDetailDTOList", memberOrderDetailDTOList);

            Integer priceSum = 0;
            for (MemberOrderDetailDTO memberOrderDetailDTO: memberOrderDetailDTOList) {
                priceSum += memberOrderDetailDTO.getItemPrice() * memberOrderDetailDTO.getItemQuantity();
            }
            model.addAttribute("priceSum", priceSum);

            return "admins/admins-order-detail";
        }

        /* admin */
        @GetMapping("/orders/admin/members/{orderNo}/cancel")
        public String cancelMemberOrderAdmin(@PathVariable(name="orderNo") Long orderNo) {
            memberOrderService.cancelMemberOrder(orderNo);
            return "redirect:/orders/admin/members";
        }

    }
