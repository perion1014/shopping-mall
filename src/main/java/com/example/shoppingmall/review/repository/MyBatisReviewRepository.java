package com.example.shoppingmall.review.repository;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.form.ReviewSearchForm;
import com.example.shoppingmall.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public String getItemSize(Long memberOrderDetailNo) { return reviewMapper.getItemSize(memberOrderDetailNo); }

    @Override
    public String getMemberId(Long memberNo) { return reviewMapper.getMemberId(memberNo); }

    @Override
    public void deleteReview(Long reviewNo) { reviewMapper.deleteReview(reviewNo); }

    @Override
    public List<Review> findMReviewByPaging(int startPage, int pagePerReview, Long memberNo) {
        return reviewMapper.findMReviewByPaging(startPage,pagePerReview,memberNo);
    }

    @Override
    public Long countMemberReview(Long memberNo) { return reviewMapper.countMemberReview(memberNo); }

    @Override
    public List<Review> findReviewByPaging(Map<String, Integer> pagingSettings) {
        return reviewMapper.findReviewByPaging(pagingSettings);
    }

    @Override
    public Long countReview() { return reviewMapper.countReview(); }

    @Override
    public String getItemName(Long itemNo) { return reviewMapper.getItemName(itemNo); }

    @Override
    public List<Review> searchReviewByPaging(ReviewSearchForm reviewSearchForm) { return reviewMapper.searchReviewByPaging(reviewSearchForm);}

    @Override
    public Long countSearchedReview(ReviewSearchForm reviewSearchForm) {
        return reviewMapper.countSearchedReview(reviewSearchForm);
    }

    @Override
    public Long getSumReviewScore(Long itemNo) {
        return reviewMapper.getSumReviewScore(itemNo);
    }

    @Override
    public Long countReviewByItemNo(Long itemNo){
        return reviewMapper.countReviewByItemNo(itemNo);
    }

    @Override
    public void updateItemGrade(float itemGrade, Long itemNo){
        reviewMapper.updateItemGrade(itemGrade, itemNo);
    }

    @Override
    public Long getItemNoByReviewNo(Long reviewNo) {
       return reviewMapper.getItemNoByReviewNo(reviewNo);
    }
}
