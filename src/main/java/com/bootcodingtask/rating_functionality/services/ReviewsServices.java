package com.bootcodingtask.rating_functionality.services;

import com.bootcodingtask.rating_functionality.models.ReviewsResponse;
import com.bootcodingtask.rating_functionality.entities.MockDrives;
import com.bootcodingtask.rating_functionality.entities.Reviews;
import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.repositories.MockDrivesRepository;
import com.bootcodingtask.rating_functionality.repositories.ReviewsRepository;
import com.bootcodingtask.rating_functionality.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewsServices {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MockDrivesRepository mockDrivesRepository;

    public Reviews saveOrUpdateReview(Integer userId, Integer mockDriveId, Reviews newReview) {
        Optional<Users> user = usersRepository.findById(userId);
        Optional<MockDrives> mockDrive = mockDrivesRepository.findById(mockDriveId);

        if (!user.isPresent()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        if (!mockDrive.isPresent()) {
            throw new IllegalArgumentException("Mock Drive not found with ID: " + mockDriveId);
        }

        Optional<Reviews> existingReview = reviewsRepository.findByUserUserIdAndMockDriveMockDriveId(userId, mockDriveId);

        if (existingReview.isPresent()) {
            Reviews reviewToUpdate = existingReview.get();
            updateReviewFields(reviewToUpdate, newReview);
            return reviewsRepository.save(reviewToUpdate);
        } else {
            newReview.setUser(user.get());
            newReview.setMockDrive(mockDrive.get());
            return reviewsRepository.save(newReview);
        }
    }

    public Reviews updateReviewById(Integer reviewId, Reviews updatedReview) {
        Reviews existingReview = reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + reviewId));

        updateReviewFields(existingReview, updatedReview);
        return reviewsRepository.save(existingReview);
    }

    private void updateReviewFields(Reviews existingReview, Reviews newReview) {
        if (newReview.getRating() != null) {
            existingReview.setRating(newReview.getRating());
        }
        if (newReview.getReview() != null) {
            existingReview.setReview(newReview.getReview());
        }
    }

    public Reviews updateReviewByUserAndMockDrive(Integer userId, Integer mockDriveId, Reviews updatedReview) {
        Reviews existingReview = reviewsRepository.findByUserUserIdAndMockDriveMockDriveId(userId, mockDriveId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Review not found for User ID: %d and Mock Drive ID: %d", userId, mockDriveId)));

        updateReviewFields(existingReview, updatedReview);
        return reviewsRepository.save(existingReview);
    }

    public Optional<Reviews> getReviewsById(Integer id) {
        return reviewsRepository.findById(id);
    }

    public List<Reviews> getAllReviews() {
        return reviewsRepository.findAll();
    }

    public List<Reviews> getReviewsByUserId(Integer userId) {
        if (!usersRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        return reviewsRepository.findByUserUserId(userId);
    }

    public List<Reviews> getReviewsByMockDriveId(Integer mockDriveId) {
        if (!mockDrivesRepository.existsById(mockDriveId)) {
            throw new IllegalArgumentException("Mock Drive not found with ID: " + mockDriveId);
        }
        return reviewsRepository.findByMockDriveMockDriveId(mockDriveId);
    }

    public Optional<Reviews> getReviewByUserAndMockDrive(Integer userId, Integer mockDriveId) {
        if (!usersRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        if (!mockDrivesRepository.existsById(mockDriveId)) {
            throw new IllegalArgumentException("Mock Drive not found with ID: " + mockDriveId);
        }
        return reviewsRepository.findByUserUserIdAndMockDriveMockDriveId(userId, mockDriveId);
    }

    public void deleteReviewById(Integer reviewId) {
        if (!reviewsRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("Review not found with ID: " + reviewId);
        }
        reviewsRepository.deleteById(reviewId);
    }


    public ReviewsResponse mapToReviewsResponse(Reviews review) {
        ReviewsResponse reviewsResponse = new ReviewsResponse();
        reviewsResponse.setReview_id(review.getReview_id());
        reviewsResponse.setUser_id(review.getUser().getUserId());
        reviewsResponse.setMock_drive_id(review.getMockDrive().getMockDriveId());
        reviewsResponse.setRating(review.getRating());
        reviewsResponse.setReview(review.getReview());
        reviewsResponse.setCreatedAt(review.getCreatedAt().toString());
        reviewsResponse.setUpdatedAt(review.getUpdatedAt().toString());
        return reviewsResponse;
    }
}