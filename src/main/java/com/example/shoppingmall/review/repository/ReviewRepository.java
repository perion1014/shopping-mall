package com.example.shoppingmall.review.repository;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;

import java.util.List;

public interface ReviewRepository {
    void addReview(ReviewAddDTO reviewAddDTO);

    List<Review> findByItemNo(Long itemNo);

    Long getItemStockNo(Long memberOrderNo);

    String getItemSize(Long itemStockNo);

    String getMemberId(Long memberNo);
}
