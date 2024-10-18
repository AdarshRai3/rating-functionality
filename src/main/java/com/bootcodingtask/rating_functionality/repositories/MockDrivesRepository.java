package com.bootcodingtask.rating_functionality.repositories;

import com.bootcodingtask.rating_functionality.entities.MockDrives;
import com.bootcodingtask.rating_functionality.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockDrivesRepository extends JpaRepository<MockDrives,Integer> {

}
