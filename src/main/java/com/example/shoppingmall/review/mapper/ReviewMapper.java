package com.example.shoppingmall.review.mapper;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void addReview(ReviewAddDTO reviewAddDTO);

    List<Review> findByItemNo(Long itemNo);
}
