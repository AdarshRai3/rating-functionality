package com.bootcodingtask.rating_functionality.services;

import com.bootcodingtask.rating_functionality.models.MockdrivesResponse;
import com.bootcodingtask.rating_functionality.entities.MockDrives;
import com.bootcodingtask.rating_functionality.repositories.MockDrivesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MockdrivesServices {
    @Autowired
    MockDrivesRepository mockDrivesRepository;



    public MockDrives createMockdrives(MockDrives mockDrives){
        return mockDrivesRepository.save(mockDrives);
    }

    public Optional<MockDrives> getMockDriveById(Integer id){
        return mockDrivesRepository.findById(id);
    }
    public List<MockDrives> getAllMockdrives(){
        return mockDrivesRepository.findAll();
    }
    public MockDrives updateMockDrives(Integer id, MockDrives updateMockDrive){
        Optional<MockDrives> existingMockDrive = mockDrivesRepository.findById(id);
        if(existingMockDrive.isPresent()){
            MockDrives mockDrives = existingMockDrive.get();
            mockDrives.setTitle(updateMockDrive.getTitle());
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
