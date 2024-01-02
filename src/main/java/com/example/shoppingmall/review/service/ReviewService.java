package com.example.shoppingmall.review.service;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.dto.ReviewDTO;
import com.example.shoppingmall.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.shoppingmall.review.dto.ReviewDTO.reivewToReviewDTO;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    // 리뷰 등록
    public void addReview(ReviewAddDTO reviewAddDTO) {
        reviewRepository.addReview(reviewAddDTO);
    }

    public List<ReviewDTO> getReviewListByitemNo(Long itemNo) {

        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        List<Review> reviews = reviewRepository.findByItemNo(itemNo);

        for (Review review : reviews) {

            Long memberOrderNo = review.getMemberOrderNo();
            Long itemStockNo = reviewRepository.getItemStockNo(memberOrderNo);

            String itemSize = reviewRepository.getItemSize(itemStockNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            reviewDTOList.add(reivewToReviewDTO(review,itemSize,memberId));

        }

        return reviewDTOList;
    }

    public void deleteReview(Long reviewNo) {
    }
}
