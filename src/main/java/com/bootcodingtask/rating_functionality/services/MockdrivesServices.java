package com.bootcodingtask.rating_functionality.services;

import com.bootcodingtask.rating_functionality.entities.Reviews;
import com.bootcodingtask.rating_functionality.models.MockdrivesResponse;
import com.bootcodingtask.rating_functionality.entities.MockDrives;
import com.bootcodingtask.rating_functionality.repositories.MockDrivesRepository;
import com.bootcodingtask.rating_functionality.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class MockdrivesServices {
    @Autowired
    MockDrivesRepository mockDrivesRepository;

    @Autowired
    ReviewsRepository reviewsRepository;


    public MockDrives createMockdrives(MockDrives mockDrives){
        return mockDrivesRepository.save(mockDrives);
    }

    public Optional<MockDrives> getMockDriveById(Integer id) {
        Optional<MockDrives> mockDriveOptional = mockDrivesRepository.findById(id);
        if (mockDriveOptional.isPresent()) {
            MockDrives mockDrive = mockDriveOptional.get();
            updateAverageRating(mockDrive);
            return Optional.of(mockDrive);
        }
        return Optional.empty();
    }

    public List<MockDrives> getAllMockdrives() {
        List<MockDrives> mockDrives = mockDrivesRepository.findAll();
        for (MockDrives mockDrive : mockDrives) {
            updateAverageRating(mockDrive);
        }
        return mockDrives;
    }

    private void updateAverageRating(MockDrives mockDrive) {
        List<Reviews> reviews = reviewsRepository.findByMockDriveMockDriveId(mockDrive.getMockDriveId()); // Fetch all reviews for the mock drive
        if (!reviews.isEmpty()) {
            BigDecimal sum = BigDecimal.ZERO;
            for (Reviews review : reviews) {
                sum = sum.add(review.getRating());
            }
            BigDecimal average = sum.divide(BigDecimal.valueOf(reviews.size()), 1, BigDecimal.ROUND_HALF_UP);
            mockDrive.setAvg_rating(average);
            mockDrivesRepository.save(mockDrive);
        } else {
            mockDrive.setAvg_rating(BigDecimal.ZERO);
            mockDrivesRepository.save(mockDrive);
        }
    }
    public MockDrives updateMockDrives(Integer id, MockDrives updateMockDrive){
        Optional<MockDrives> existingMockDrive = mockDrivesRepository.findById(id);
        if(existingMockDrive.isPresent()){
            MockDrives mockDrives = existingMockDrive.get();
            mockDrives.setTitle(updateMockDrive.getTitle());
            mockDrives.setQuestions(updateMockDrive.getQuestions());
            return mockDrivesRepository.save(mockDrives);
        }
        return null;
    }
    public boolean deleteMockDrivesByID(Integer id){
        if(mockDrivesRepository.existsById(id)){
            mockDrivesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public MockdrivesResponse mapToMockdrivesResponse(MockDrives mockDrive) {
        MockdrivesResponse mockdrivesResponse = new MockdrivesResponse();
        mockdrivesResponse.setMock_drive_id(mockDrive.getMockDriveId());
        mockdrivesResponse.setTitle(mockDrive.getTitle());
        mockdrivesResponse.setAvg_rating(mockDrive.getAvg_rating());
        mockdrivesResponse.setCreated_at(mockDrive.getCreatedAt().toString());
        mockdrivesResponse.setQuestions(mockDrive.getQuestions());
        return mockdrivesResponse;
    }
}
