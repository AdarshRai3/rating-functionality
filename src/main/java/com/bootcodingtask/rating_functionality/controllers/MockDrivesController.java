package com.bootcodingtask.rating_functionality.controllers;

import com.bootcodingtask.rating_functionality.entities.MockDrives;
import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.services.MockdrivesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mockdrives")
public class MockDrivesController {
    @Autowired
    MockdrivesServices mockdrivesServices;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody MockDrives myMockDrive){

        try{
            MockDrives createdMockdrives = mockdrivesServices.createMockdrives(myMockDrive);
            return new ResponseEntity<>(createdMockdrives, HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllMockdrives(){
        List<MockDrives> mockDrives = mockdrivesServices.getAllMockdrives();
        return ResponseEntity.ok(mockDrives);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getMockdrivesById(@PathVariable Integer id){
        Optional<MockDrives> mockDrive = mockdrivesServices.getMockDriveById(id);
        if(mockDrive.isPresent()){
            return new ResponseEntity<>(mockDrive,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateMockdrivesById(@PathVariable Integer id, @RequestBody MockDrives newEntry){
        MockDrives oldMockDrives = mockdrivesServices.getMockDriveById(id).orElse(null);
        if(oldMockDrives!=null){
            oldMockDrives.setTitle(newEntry.getTitle()!=null || newEntry.getTitle().equals("")?newEntry.getTitle():oldMockDrives.getTitle() );
            oldMockDrives.setQuestions(newEntry.getQuestions()!=null || newEntry.getQuestions().equals("")?newEntry.getQuestions():oldMockDrives.getQuestions());
            return new ResponseEntity<>(oldMockDrives,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteMockdrivesById(@PathVariable Integer id){
        mockdrivesServices.deleteMockDrivesByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

 }
