package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

import static com.example.shoppingmall.order.validation.OrderValidationGroup.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberOrderAddDTO {

    private Long memberOrderNo;
    private Long memberNo;
    private Timestamp orderTime;
    @NotBlank(message = "휴대폰 번호를 입력해주세요.", groups = OrderNotBlankGroup.class)
    @Pattern(regexp = "^01(?:0|1|[6-9])\\d{7,8}$", message = "유효하지 않은 휴대폰 번호입니다.", groups = OrderPatternGroup.class)
    private String orderHp;
    @NotNull(message = "우편번호를 입력해주세요.", groups = OrderNotBlankGroup.class)
    private Integer orderPostalCode;
    @NotBlank(message = "기본주소를 입력해주세요.", groups = OrderNotBlankGroup.class)
    private String orderAddressBasic;
    @Pattern(regexp = "[가-힣]{0,50}", message = "최대 50자까지 가능합니다.", groups = OrderPatternGroup.class)
    private String orderAddressDetail;
    @NotBlank(message = "성명을 입력해주세요.", groups = OrderNotBlankGroup.class)
    @Pattern(regexp = "[가-힣]{2,40}", message = "잘못된 이름 형식입니다.", groups = OrderPatternGroup.class)
    private String receiverName;
    private Integer orderStatus;

    private List<MemberOrderDetailDTO> memberOrderDetailDTOList;
    private List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList;

    public static MemberOrder toMemberOrder(Long memberNo, MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberOrderNo(memberOrderAddDTO.getMemberOrderNo());
        memberOrder.setMemberNo(memberNo);
        memberOrder.setOrderTime(memberOrderAddDTO.getOrderTime());
        memberOrder.setOrderHp(memberOrderAddDTO.getOrderHp());
        memberOrder.setOrderPostalCode(memberOrderAddDTO.getOrderPostalCode());
        memberOrder.setOrderAddressBasic(memberOrderAddDTO.getOrderAddressBasic());
        memberOrder.setOrderAddressDetail(memberOrderAddDTO.getOrderAddressDetail());
        memberOrder.setReceiverName(memberOrderAddDTO.getReceiverName());
        memberOrder.setOrderStatus(1);
        //memberOrder.setMemberOrderDetailDTOList(memberOrderAddDTO.getMemberOrderDetailDTOList());
        return memberOrder;
    }

    public static MemberOrderDTO toMemberOrderDTO(MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrderDTO memberOrderDTO = new MemberOrderDTO();
        memberOrderDTO.setMemberOrderNo(memberOrderAddDTO.getMemberOrderNo());
        memberOrderDTO.setMemberNo(memberOrderAddDTO.getMemberNo());
        memberOrderDTO.setOrderTime(memberOrderAddDTO.getOrderTime());
        memberOrderDTO.setOrderHp(memberOrderAddDTO.getOrderHp());
        memberOrderDTO.setOrderPostalCode(memberOrderAddDTO.getOrderPostalCode());
        memberOrderDTO.setOrderAddressBasic(memberOrderAddDTO.getOrderAddressBasic());
        memberOrderDTO.setOrderAddressDetail(memberOrderAddDTO.getOrderAddressDetail());
        memberOrderDTO.setReceiverName(memberOrderAddDTO.getReceiverName());
        memberOrderDTO.setOrderStatus(memberOrderAddDTO.getOrderStatus());
        return memberOrderDTO;
    }

}
