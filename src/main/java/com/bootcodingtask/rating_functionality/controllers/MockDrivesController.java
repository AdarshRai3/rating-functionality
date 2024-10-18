package com.bootcodingtask.rating_functionality.controllers;

import com.bootcodingtask.rating_functionality.models.MockdrivesResponse;
import com.bootcodingtask.rating_functionality.entities.MockDrives;
import com.bootcodingtask.rating_functionality.services.MockdrivesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mockdrives")
public class MockDrivesController {

    @Autowired
    private MockdrivesServices mockdrivesServices;

    @PostMapping
    public ResponseEntity<MockdrivesResponse> createMockDrive(@RequestBody MockDrives myMockDrive) {
        try {
            MockDrives createdMockDrive = mockdrivesServices.createMockdrives(myMockDrive);
            MockdrivesResponse response = mockdrivesServices.mapToMockdrivesResponse(createdMockDrive);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<MockdrivesResponse>> getAllMockdrives() {
        List<MockDrives> mockDrives = mockdrivesServices.getAllMockdrives();
        List<MockdrivesResponse> responseList = mockDrives.stream()
                .map(mockdrivesServices::mapToMockdrivesResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<MockdrivesResponse> getMockdrivesById(@PathVariable Integer id) {
        Optional<MockDrives> mockDrive = mockdrivesServices.getMockDriveById(id);
        if (mockDrive.isPresent()) {
            MockdrivesResponse response = mockdrivesServices.mapToMockdrivesResponse(mockDrive.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<MockdrivesResponse> updateMockdrivesById(@PathVariable Integer id, @RequestBody MockDrives newEntry) {
        MockDrives updatedMockDrive = mockdrivesServices.updateMockDrives(id, newEntry);
        if (updatedMockDrive != null) {
            MockdrivesResponse response = mockdrivesServices.mapToMockdrivesResponse(updatedMockDrive);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteMockdrivesById(@PathVariable Integer id) {
        if (mockdrivesServices.deleteMockDrivesByID(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}