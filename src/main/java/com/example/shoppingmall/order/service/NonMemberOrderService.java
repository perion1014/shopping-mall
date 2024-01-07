package com.example.shoppingmall.order.service;

import com.example.shoppingmall.cart.dto.nonMemberCartAddDTO;
import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import com.example.shoppingmall.order.dto.NonMemberOrderAddDTO;
import com.example.shoppingmall.order.dto.NonMemberOrderDetailAddDTO;
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

}
