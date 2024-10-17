package com.bootcodingtask.rating_functionality.repositories;

import com.bootcodingtask.rating_functionality.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

}
