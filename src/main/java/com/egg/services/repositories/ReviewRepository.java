package com.egg.services.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egg.services.entities.Review;

public interface ReviewRepository extends JpaRepository <Review,Integer> {

	
	  @Query("SELECT r FROM Review r WHERE r.id = id")
		Optional<Review> findById(@Param("id") Integer id);
	  
	  @Query("SELECT r FROM Review r WHERE r.content = content")
		Optional<Review> findByContent(@Param("content") String content);
	 
}
