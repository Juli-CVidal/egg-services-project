package com.egg.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.services.entities.Offering;


@Repository
public interface OfferingRepository extends JpaRepository<Offering,Integer>{

	@Query("SELECT o FROM Offering o WHERE o.id = id")
	Optional<Offering> findById(@Param("id") Integer id);
	
	@Query("SELECT o FROM Offering o WHERE o.serviceType = serviceType")
	List<Offering> findByServiceType(@Param("serviceType") String serviceType);
	
	@Query("SELECT o FROM Offering o WHERE o.state = state")
	List<Offering> findByState(@Param("state") Boolean state);
	
	
	//NOT IMPLEMENTED YET - Get all offerings from a Supplier
	/*
	@Query("SELECT o FROM Offering o WHERE o.supplierId = id")
	List<Offering> findBySupplier(@Param("id") Integer id);
	*/
}


