package com.example.shoppingmall.admin.mapper;

import com.example.shoppingmall.admin.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin findById (String adminId);

}
