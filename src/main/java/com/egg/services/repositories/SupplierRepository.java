package com.egg.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.egg.services.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Query("SELECT s FROM Supplier s WHERE s.id = id")
	Optional<Supplier> findById(@Param("id") Integer id);

	@Query("SELECT s FROM Supplier s WHERE s.name = name")
	// At this moment, the name is not an unique field
	// otherwise, change to Optional<Supplier>
	List<Supplier> findByName(@Param("name") String name);

	@Query("SELECT s FROM Supplier s WHERE s.state = state")
	List<Supplier> findByState(@Param("state") Boolean state);

}