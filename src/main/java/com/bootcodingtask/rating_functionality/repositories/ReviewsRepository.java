package com.bootcodingtask.rating_functionality.repositories;

import com.bootcodingtask.rating_functionality.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Integer> {
}
