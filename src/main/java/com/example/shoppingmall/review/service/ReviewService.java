package com.example.shoppingmall.review.service;

import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.dto.ReviewDTO;
import com.example.shoppingmall.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        }
        return reviewDTOList;
    }
}
