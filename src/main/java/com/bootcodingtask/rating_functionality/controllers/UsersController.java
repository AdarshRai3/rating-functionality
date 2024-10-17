package com.bootcodingtask.rating_functionality.controllers;

import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("api/users")
public class UsersController {

    @Autowired
    private UsersServices usersServices;
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users myUser){

        try{
            Users createdUser = usersServices.createUser(myUser);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<Users> users = usersServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("id/{id}")
    public  ResponseEntity<?> getUserById(@PathVariable Integer id){
        Optional<Users> user = usersServices.getUsersById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

     @PutMapping("id/{id}")
    public ResponseEntity<?> updateUserById( @PathVariable Integer id,@RequestBody Users newEntry ){
        Users oldUser = usersServices.getUsersById(id).orElse(null);
        if(oldUser!=null){
            oldUser.setUsername(newEntry.getUsername()!=null || !newEntry.getUsername().equals("")?newEntry.getUsername():oldUser.getUsername());
            oldUser.setEmail(newEntry.getEmail()!=null || !newEntry.getEmail().equals("")?newEntry.getEmail():oldUser.getEmail());
            oldUser.setPasswordHash(newEntry.getPasswordHash()!=null || !newEntry.getPasswordHash().equals("")?newEntry.getPasswordHash():oldUser.getPasswordHash());
            return new  ResponseEntity<>(oldUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
