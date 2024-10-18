package com.bootcodingtask.rating_functionality.services;

import com.bootcodingtask.rating_functionality.entities.MockDrives;
import com.bootcodingtask.rating_functionality.entities.Reviews;
import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.repositories.MockDrivesRepository;
import com.bootcodingtask.rating_functionality.repositories.ReviewsRepository;
import com.bootcodingtask.rating_functionality.repositories.UsersRepository;
import org.apache.catalina.User;
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

            if (user.isPresent() && mockDrive.isPresent()) {

                Optional<Reviews> existingReview = reviewsRepository.findByUserUserIdAndMockDriveMockDriveId(userId, mockDriveId);

                if (existingReview.isPresent()) {

                    Reviews reviewToUpdate = existingReview.get();
                    reviewToUpdate.setRating(newReview.getRating());
                    reviewToUpdate.setReview(newReview.getReview());
                    return reviewsRepository.save(reviewToUpdate);
                } else {

                    newReview.setUser(user.get());
                    newReview.setMockDrive(mockDrive.get());
                    return reviewsRepository.save(newReview);
                }
            } else {
                throw new IllegalArgumentException("Invalid User ID or Mock Drive ID");
            }
        }

        public Optional<Reviews> getReviewsById(Integer id){
        return reviewsRepository.findById(id);
    }
        public List<Reviews> getAllMockdrives(){
        return reviewsRepository.findAll();
    }
        public List<Reviews> getReviewsByUserId(Integer userId) {
          return reviewsRepository.findByUserUserId(userId);
        }

        public List<Reviews> getReviewsByMockDriveId(Integer mockDriveId) {
           return reviewsRepository.findByMockDriveMockDriveId(mockDriveId);
        }

        public Optional<Reviews> getReviewByUserAndMockDrive(Integer userId, Integer mockDriveId) {
           return reviewsRepository.findByUserUserIdAndMockDriveMockDriveId(userId, mockDriveId);
        }
    }

