package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.member.validation.MemberValidationGroup;
import com.example.shoppingmall.order.domain.NonMemberOrder;
import com.example.shoppingmall.order.validation.OrderValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import static com.example.shoppingmall.order.validation.OrderValidationGroup.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonMemberOrderAddDTO {

    @NotBlank(message = "성명을 입력해주세요.", groups = OrderNotBlankGroup.class)
    private String nonMemberName;
    @NotBlank(message = "휴대폰 번호를 입력해주세요.", groups = OrderNotBlankGroup.class)
    @Pattern(regexp = "^01(?:0|1|[6-9])\\d{7,8}$", message = "유효하지 않은 휴대폰 번호입니다.", groups = OrderPatternGroup.class)
    private String orderHp;
    @NotBlank(message = "이메일을 입력해주세요.", groups = OrderNotBlankGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,50}$", message = "유효하지 않은 이메일 주소입니다.", groups = OrderPatternGroup.class)
    private String orderEmail;
    @NotBlank(message = "성명을 입력해주세요.", groups = OrderNotBlankGroup.class)
    private String receiverName;
    @NotNull(message = "우편번호를 입력해주세요.", groups = OrderNotBlankGroup.class)
    private Integer orderPostalCode;
    @NotBlank(message = "기본주소를 입력해주세요.", groups = OrderNotBlankGroup.class)
    private String orderAddressBasic;
    @Pattern(regexp = "[가-힣a-zA-Z0-9]{0,50}", message = "최대 50자까지 가능합니다.", groups = OrderPatternGroup.class)
    private String orderAddressDetail;

    public static NonMemberOrder toNonMemberOrder(NonMemberOrderAddDTO nonMemberOrderAddDTO){
        NonMemberOrder nonMemberOrder = new NonMemberOrder();
        nonMemberOrder.setNonMemberOrderNo(null);
        nonMemberOrder.setOrderTime(null);
        nonMemberOrder.setOrderStatus(null);
        nonMemberOrder.setNonMemberName(nonMemberOrderAddDTO.getNonMemberName());
        nonMemberOrder.setOrderHp(nonMemberOrderAddDTO.getOrderHp());
        nonMemberOrder.setOrderEmail(nonMemberOrderAddDTO.getOrderEmail());
        nonMemberOrder.setReceiverName(nonMemberOrderAddDTO.getReceiverName());
        nonMemberOrder.setOrderPostalCode(nonMemberOrderAddDTO.getOrderPostalCode());
        nonMemberOrder.setOrderAddressBasic(nonMemberOrderAddDTO.getOrderAddressBasic());
        nonMemberOrder.setOrderAddressDetail(nonMemberOrderAddDTO.getOrderAddressDetail());
        return nonMemberOrder;
    }

}