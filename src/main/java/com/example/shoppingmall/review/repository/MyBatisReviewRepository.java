package com.example.shoppingmall.review.repository;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisReviewRepository implements ReviewRepository{

    private final ReviewMapper reviewMapper;

    @Override
    public void addReview(ReviewAddDTO reviewAddDTO) {
        reviewMapper.addReview(reviewAddDTO);
    }

    @Override
    public List<Review> findByItemNo(Long itemNo) { return reviewMapper.findByItemNo(itemNo); }

    @Override
    public Long getItemStockNo(Long memberOrderNo) { return reviewMapper.getItemStockNo(memberOrderNo);}

    @Override
    public String getItemSize(Long itemStockNo) { return reviewMapper.getItemSize(itemStockNo); }

    @Override
    public  String getMemberId(Long memberNo) { return reviewMapper.getMemberId(memberNo); }
}
