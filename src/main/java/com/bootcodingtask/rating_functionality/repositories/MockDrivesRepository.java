package com.bootcodingtask.rating_functionality.repositories;

import com.bootcodingtask.rating_functionality.entities.MockDrives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockDrivesRepository extends JpaRepository<MockDrives,Integer> {

}
