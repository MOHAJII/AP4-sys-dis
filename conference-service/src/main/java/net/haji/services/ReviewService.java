package net.haji.services;

import net.haji.dtos.ReviewCreateDto;
import net.haji.dtos.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewCreateDto dto);
    ReviewDto getReviewById(Long id);
    List<ReviewDto> getReviewsByConferenceId(Long conferenceId);
    List<ReviewDto> getAllReviews();
    void deleteReview(Long id);
}