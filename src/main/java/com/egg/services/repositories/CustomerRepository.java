package com.egg.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.services.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

	
	 @Query("SELECT c FROM Customer c WHERE c.id = id")
	Optional<Customer> findById(@Param("id") Integer id);
	 
	
	 @Query("SELECT c FROM Customer c WHERE c.name = name")
	List<Customer> findByName(@Param("name") String name);
	 
}
