package net.haji.services;

import net.haji.dtos.ReviewCreateDto;
import net.haji.dtos.ReviewDto;
import net.haji.entities.Conference;
import net.haji.entities.Review;
import net.haji.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ConferenceService conferenceService;

    @Override
    public ReviewDto createReview(ReviewCreateDto dto) {
        if (dto.getStars() < 1 || dto.getStars() > 5) {
            throw new IllegalArgumentException("Stars must be between 1 and 5");
        }

        Conference conference = conferenceService.getConferenceEntityById(dto.getConferenceId());

        Review review = new Review(
                LocalDateTime.now(),
                dto.getText(),
                dto.getStars(),
                conference
        );

        Review savedReview = reviewRepository.save(review);
        return convertToDto(savedReview);
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return convertToDto(review);
    }

    @Override
    public List<ReviewDto> getReviewsByConferenceId(Long conferenceId) {
        return reviewRepository.findByConferenceId(conferenceId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    private ReviewDto convertToDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getDate(),
                review.getText(),
                review.getStars(),
                review.getConference().getId()
        );
    }
}