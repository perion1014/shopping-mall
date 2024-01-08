package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowNonMemberOrderDetailDTO {

    private Long nonMemberOrderNo;
    private String nonMemberName;
    private String receiverName;
    private String orderHp;
    private String orderAddressBasic;
    private String itemThumb;
    private Integer orderStatus;

    private List<NonMemberOrderDetail> nonMemberOrderDetailAddDTOList;

    public static ShowNonMemberOrderDetailDTO toShowNonMemberOrderDetailDTO(NonMemberOrder nonMemberOrder, List<NonMemberOrderDetail> nonMemberOrderDetailList){
        ShowNonMemberOrderDetailDTO showNonMemberOrderDetailDTO = new ShowNonMemberOrderDetailDTO();
        showNonMemberOrderDetailDTO.setNonMemberOrderNo(nonMemberOrder.getNonMemberOrderNo());
        showNonMemberOrderDetailDTO.setNonMemberName(nonMemberOrder.getNonMemberName());
        showNonMemberOrderDetailDTO.setReceiverName(nonMemberOrder.getReceiverName());
        showNonMemberOrderDetailDTO.setOrderHp(nonMemberOrder.getOrderHp());
        showNonMemberOrderDetailDTO.setOrderAddressBasic(nonMemberOrder.getOrderAddressBasic());
        showNonMemberOrderDetailDTO.setOrderStatus(nonMemberOrder.getOrderStatus());
        showNonMemberOrderDetailDTO.setNonMemberOrderDetailAddDTOList(nonMemberOrderDetailList);

        return showNonMemberOrderDetailDTO;
    }

}
