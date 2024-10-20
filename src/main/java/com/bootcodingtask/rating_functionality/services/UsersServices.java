package com.bootcodingtask.rating_functionality.services;

import com.bootcodingtask.rating_functionality.models.UsersResponse;
import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Optional<Users> getUsersById(Integer id) {
        return usersRepository.findById(id);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users updateUser(Integer id, Users updateUser) {
        Optional<Users> existingUserOpt = usersRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            Users user = existingUserOpt.get();
            user.setUsername(updateUser.getUsername() != null ? updateUser.getUsername() : user.getUsername());
            user.setEmail(updateUser.getEmail() != null ? updateUser.getEmail() : user.getEmail());
            user.setPasswordHash(updateUser.getPasswordHash() != null ? updateUser.getPasswordHash() : user.getPasswordHash());
            return usersRepository.save(user);
        }
        return null;
    }

    public boolean deleteUserByID(Integer id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public UsersResponse mapToUsersResponse(Users user) {
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUser_id(user.getUserId());
        usersResponse.setUsername(user.getUsername());
        usersResponse.setEmail(user.getEmail());
        usersResponse.setCreatedAt(user.getCreatedAt().toString());
        return usersResponse;
    }
}
