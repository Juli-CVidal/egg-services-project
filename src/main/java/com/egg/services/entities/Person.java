package com.egg.services.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.egg.services.enums.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person{

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Integer id;

	@NotBlank(message = "No valid name entered")
	@Column(unique = true)
	protected String name;

	@NotBlank(message = "No lastName name entered")
	protected String lastname;

	@NotBlank(message = "No valid name entered")
	@Email(message = "The mail must be name@domain.com")
	protected String mail;

	@Column(columnDefinition="MEDIUMTEXT")
	protected String image;

	@NotBlank(message = "Invalid password")
	@Size(min = 8, message = "The password has to be at least 8 chars")
	protected String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	protected Rol rol;

	public Person(String name,String lastname, String phoneNumber,
			String mail, String image, String password, Rol rol) {
		this.name = name;
		this.lastname = lastname;
		this.mail = mail;
		this.image = image;
		this.password = password;
		this.rol = rol;
	}

}
