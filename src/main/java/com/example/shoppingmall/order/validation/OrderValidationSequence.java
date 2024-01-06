package com.example.shoppingmall.order.validation;

import jakarta.validation.GroupSequence;

import static com.example.shoppingmall.member.validation.MemberValidationGroup.MemberNotBlankGroup;
import static com.example.shoppingmall.member.validation.MemberValidationGroup.MemberPatternGroup;
import static com.example.shoppingmall.order.validation.OrderValidationGroup.*;

@GroupSequence({OrderNotBlankGroup.class, OrderPatternGroup.class })
public interface OrderValidationSequence {
}
