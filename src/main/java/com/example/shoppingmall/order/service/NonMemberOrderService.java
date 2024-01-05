package com.example.shoppingmall.order.service;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import com.example.shoppingmall.order.dto.NonMemberOrderAddDTO;
import com.example.shoppingmall.order.dto.NonMemberOrderDetailAddDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.shoppingmall.order.repository.nonMemberOrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NonMemberOrderService {

    private final nonMemberOrderRepository nonMemberOrderRepository;

    public void saveNonMemberOrder(NonMemberOrderAddDTO nonMemberOrderAddDTO){

        NonMemberOrder nonMemberOrder = NonMemberOrderAddDTO.toNonMemberOrder(nonMemberOrderAddDTO);
        nonMemberOrderRepository.saveNonMemberOrder(nonMemberOrder);

    }

    public void saveNonMemberOrderDetail(List<NonMemberOrderDetailAddDTO> nonMemberOrderDetailAddDTOList){

        Long LastNonMemberOrderNo = nonMemberOrderRepository.getMaxNoFromNonMemberOrderDB();
        //System.out.println("논멤버오더디테일에 들어갈 오더넘버 : " + LastNonMemberOrderNo);
        for(int i = 0; i < nonMemberOrderDetailAddDTOList.size(); i++){
            NonMemberOrderDetail nonMemberOrderDetail = NonMemberOrderDetailAddDTO.toNonMemberOrderDetail(nonMemberOrderDetailAddDTOList.get(i), LastNonMemberOrderNo);
            nonMemberOrderRepository.saveNonMemberOrderDetail(nonMemberOrderDetail);
        }

    }

}
