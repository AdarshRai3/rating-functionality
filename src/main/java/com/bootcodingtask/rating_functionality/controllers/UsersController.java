package com.bootcodingtask.rating_functionality.controllers;

import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.models.UsersResponse;
import com.bootcodingtask.rating_functionality.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UsersServices usersServices;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users myUser) {
        try {
            Users createdUser = usersServices.createUser(myUser);
            UsersResponse userResponse = usersServices.mapToUsersResponse(createdUser);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = usersServices.getAllUsers();
        List<UsersResponse> usersResponses = users.stream()
                .map(usersServices::mapToUsersResponse)
                .toList();
        return ResponseEntity.ok(usersResponses);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Optional<Users> user = usersServices.getUsersById(id);
        return user.map(value -> new ResponseEntity<>(usersServices.mapToUsersResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer id, @RequestBody Users newEntry) {
        Users updatedUser = usersServices.updateUser(id, newEntry);
        return updatedUser != null ? new ResponseEntity<>(usersServices.mapToUsersResponse(updatedUser), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        boolean isDeleted = usersServices.deleteUserByID(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
