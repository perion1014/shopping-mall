package com.example.shoppingmall.order.service;

import com.example.shoppingmall.cart.dto.nonMemberCartAddDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import com.example.shoppingmall.order.dto.NonMemberOrderAddDTO;
import com.example.shoppingmall.order.dto.NonMemberOrderDetailAddDTO;
import com.example.shoppingmall.order.dto.ShowNonMemberOrderDetailDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.shoppingmall.order.repository.nonMemberOrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NonMemberOrderService {

    private final nonMemberOrderRepository nonMemberOrderRepository;
    private final ItemService itemService;

    public void saveNonMemberOrder(NonMemberOrderAddDTO nonMemberOrderAddDTO){

        NonMemberOrder nonMemberOrder = NonMemberOrderAddDTO.toNonMemberOrder(nonMemberOrderAddDTO);
        nonMemberOrderRepository.saveNonMemberOrder(nonMemberOrder);

    }

    public void saveNonMemberOrderDetail(List<NonMemberOrderDetailAddDTO> nonMemberOrderDetailAddDTOList, HttpServletRequest req){

        Long LastNonMemberOrderNo = nonMemberOrderRepository.getMaxNoFromNonMemberOrderDB();
        //System.out.println("논멤버오더디테일에 들어갈 오더넘버 : " + LastNonMemberOrderNo);
        for(int i = 0; i < nonMemberOrderDetailAddDTOList.size(); i++){
            NonMemberOrderDetail nonMemberOrderDetail = NonMemberOrderDetailAddDTO.toNonMemberOrderDetail(nonMemberOrderDetailAddDTOList.get(i), LastNonMemberOrderNo);
            nonMemberOrderRepository.saveNonMemberOrderDetail(nonMemberOrderDetail);
        }

        req.getSession().setAttribute("nonMemberOrderDetailAddDTOList", null);

    }

    public void setNowOrderNo(HttpSession session){
        Long MaxOrderNo = nonMemberOrderRepository.getMaxNoFromNonMemberOrderDB();
        session.setAttribute("nowOrderNo", MaxOrderNo);
    }

    public void deleteOrderedCarts(HttpSession session){
        List<nonMemberCartAddDTO> nonmemberCartList = (List<nonMemberCartAddDTO>)session.getAttribute("nonmemberCartList");
        List<Long> deleteNonmemberCartIndexList = (List<Long>)session.getAttribute("deleteNonmemberCartIndexList");

        //인덱스 밀림 현상때문에 뒤에서부터 지워주기
        for(int i = deleteNonmemberCartIndexList.size() - 1; i >= 0; i--){
            int idx = deleteNonmemberCartIndexList.get(i).intValue();
            nonmemberCartList.remove(idx);
//            System.out.println("삭제해야 할 인덱스 : " + deleteNonmemberCartIndexList.get(i));
        }

//        System.out.println("인덱스 삭제 후 남은 인덱스 개수 : " + nonmemberCartList.size());
        if(nonmemberCartList.size() == 0){
           session.setAttribute("nonmemberCartList", null);
        } else{
        session.setAttribute("nonmemberCartList", nonmemberCartList);
        }
    }

    public ShowNonMemberOrderDetailDTO getNonmemberOrderDetailFromOrderNoAndOrderName(Long nonMemberOrderNo, String nonMemberOrderName){

    NonMemberOrder nonMemberOrder = nonMemberOrderRepository.getNonMemberOrderFromOrderNo(nonMemberOrderNo, nonMemberOrderName);
    System.out.println(nonMemberOrder);

    if(nonMemberOrder != null){
    ShowNonMemberOrderDetailDTO showNonMemberOrderDetailDTO = new ShowNonMemberOrderDetailDTO();
//        System.out.println("db에서 꺼내온 주문번호 : " + nonMemberOrder.getNonMemberOrderNo());
//        System.out.println("db에서 꺼내온 주문자명 : " + nonMemberOrder.getNonMemberName());
//        System.out.println("db에서 꺼내온 수령자명 : " + nonMemberOrder.getReceiverName());
//        System.out.println("db에서 꺼내온 전화번호 : " + nonMemberOrder.getOrderHp());
//        System.out.println("db에서 꺼내온 기본주소 : " + nonMemberOrder.getOrderAddressBasic());
    List<NonMemberOrderDetail> nonMemberOrderDetailList = nonMemberOrderRepository.getNonMemberOrderDetailFromOrderNo(nonMemberOrderNo);
//        System.out.println(nonMemberOrderDetailList == null);
//        System.out.println(nonMemberOrderDetailList);
            for(int i = 0; i < nonMemberOrderDetailList.size(); i++){
//                System.out.println("DB에서 꺼내온 상품명 : " + nonMemberOrderDetailList.get(i).getItemName());
//                System.out.println("DB에서 꺼내온 사이즈 : " + nonMemberOrderDetailList.get(i).getItemSize());
//                System.out.println("DB에서 꺼내온걸로 꺼내온 아이템 썸네일 : " + itemService.getItemThumbByNo(nonMemberOrderDetailList.get(i).getItemNo()));
//                System.out.println("DB에서 꺼내온 상품 가격 : " + nonMemberOrderDetailList.get(i).getItemPrice());
//                System.out.println("DB에서 꺼내온 상품 수량 : " + nonMemberOrderDetailList.get(i).getItemQuantity());
                nonMemberOrderDetailList.get(i).setItemThumb(itemService.getItemThumbByNo(nonMemberOrderDetailList.get(i).getItemNo()));
            }

           showNonMemberOrderDetailDTO = ShowNonMemberOrderDetailDTO.toShowNonMemberOrderDetailDTO(nonMemberOrder, nonMemberOrderDetailList);

           System.out.println("객체 있음");

           return showNonMemberOrderDetailDTO;

    } else{
            System.out.println("해당 주문번호, 혹은 주문자명으로 등록된 주문이 없습니다.");
            return null;
    }

    }

    public void cancelNonMemberOrder(Long nonMemberOrderNo){
        nonMemberOrderRepository.cancelNonMemberOrder(nonMemberOrderNo);
    }

}
