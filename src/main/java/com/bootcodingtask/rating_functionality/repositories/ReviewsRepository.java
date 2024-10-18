package com.bootcodingtask.rating_functionality.repositories;

import com.bootcodingtask.rating_functionality.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByUserUserId(Integer userId);
    List<Reviews> findByMockDriveMockDriveId(Integer mockDriveId);
    Optional<Reviews> findByUserUserIdAndMockDriveMockDriveId(Integer userId, Integer mockDriveId);
}

