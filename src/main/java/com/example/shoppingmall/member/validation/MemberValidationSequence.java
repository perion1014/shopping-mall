package com.example.shoppingmall.member.validation;

import jakarta.validation.GroupSequence;
import static com.example.shoppingmall.member.validation.MemberValidationGroup.*;

@GroupSequence({MemberNotBlankGroup.class, MemberPatternGroup.class })
public interface MemberValidationSequence {
}
