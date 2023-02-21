package com.egg.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egg.services.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

	 @Query("SELECT p FROM Person c WHERE p.id = id")
		Optional<Person> findById(@Param("id") Integer id);
	 
	 @Query("SELECT p FROM Person p WHERE p.name = name")
		List<Person> findByName(@Param("name") String name);
	 
	 @Query("SELECT p FROM Person p WHERE p.lastname = lastname")
		List<Person> findByLastName(@Param("lastname") String lastname);
	 
	 @Query("SELECT p FROM Person p WHERE p.mail = mail")
		List<Person> findByMail(@Param("mail") String mail);
}
