package com.bootcodingtask.rating_functionality.controllers;


import com.bootcodingtask.rating_functionality.models.ReviewsResponse;
import com.bootcodingtask.rating_functionality.entities.Reviews;
import com.bootcodingtask.rating_functionality.services.ReviewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsServices reviewsServices;


    @PostMapping("/users/id/{userId}/mockdrives/id/{mockDriveId}")
    public ResponseEntity<Reviews> createOrUpdateReview(
            @PathVariable Integer userId,
            @PathVariable Integer mockDriveId,
            @RequestBody Reviews review) {
        try {
            Reviews savedReview = reviewsServices.saveOrUpdateReview(userId,mockDriveId,review);
            return new ResponseEntity<>(savedReview, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        List<Reviews> reviews = reviewsServices.getAllReviews();
        List<ReviewsResponse> responseList = reviews.stream()
                .map(reviewsServices::mapToReviewsResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<?> getReviewsById(@PathVariable Integer id) {
        Optional<Reviews> review = reviewsServices.getReviewsById(id);
        if (review.isPresent()) {
            ReviewsResponse reviewResponse = reviewsServices.mapToReviewsResponse(review.get());
            return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/id/{userId}")
    public ResponseEntity<?> getReviewsByUserId(@PathVariable Integer userId) {
        List<Reviews> reviews = reviewsServices.getReviewsByUserId(userId);
        if (!reviews.isEmpty()) {
            List<ReviewsResponse> responseList = reviews.stream()
                    .map(reviewsServices::mapToReviewsResponse)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/mockdrives/id/{mockDriveId}")
    public ResponseEntity<?> getReviewsByMockDriveId(@PathVariable Integer mockDriveId) {
        List<Reviews> reviews = reviewsServices.getReviewsByMockDriveId(mockDriveId);
        if (!reviews.isEmpty()) {
            List<ReviewsResponse> responseList = reviews.stream()
                    .map(reviewsServices::mapToReviewsResponse)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/id/{userId}/mockdrives/id/{mockDriveId}")
    public ResponseEntity<?> getReviewByUserAndMockDrive(
            @PathVariable Integer userId,
            @PathVariable Integer mockDriveId) {
        Optional<Reviews> review = reviewsServices.getReviewByUserAndMockDrive(userId, mockDriveId);
        if (review.isPresent()) {
            ReviewsResponse reviewResponse = reviewsServices.mapToReviewsResponse(review.get());
            return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
