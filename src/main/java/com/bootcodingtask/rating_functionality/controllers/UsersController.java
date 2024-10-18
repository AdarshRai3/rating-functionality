package com.bootcodingtask.rating_functionality.controllers;

import com.bootcodingtask.rating_functionality.models.UsersResponse;
import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UsersServices usersServices;

    @PostMapping
    public ResponseEntity<UsersResponse> createUser(@RequestBody Users myUser) {
        try {
            Users createdUser = usersServices.createUser(myUser);
            UsersResponse response = usersServices.mapToUsersResponse(createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsersResponse>> getAllUsers() {
        List<Users> users = usersServices.getAllUsers();
        List<UsersResponse> responseList = users.stream()
                .map(usersServices::mapToUsersResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable Integer id) {
        Optional<Users> user = usersServices.getUsersById(id);
        if (user.isPresent()) {
            UsersResponse response = usersServices.mapToUsersResponse(user.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<UsersResponse> updateUserById(@PathVariable Integer id, @RequestBody Users newEntry) {
        Users updatedUser = usersServices.updateUser(id, newEntry);
        if (updatedUser != null) {
            UsersResponse response = usersServices.mapToUsersResponse(updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        if (usersServices.deleteUserByID(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
