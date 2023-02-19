package com.egg.services.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.egg.services.enums.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
@Inheritance
@MappedSuperclass
public abstract class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@NotEmpty
	protected String name;

	@NotEmpty
	protected String lastname;

	@NotEmpty
	protected String phoneNumber;

	@NotEmpty
	@Email
	protected String mail;

	@NotEmpty
	protected String image;

	@NotEmpty
	protected String password;

	@NotEmpty
	@Enumerated(EnumType.STRING)
	protected Rol rol;

	public Person(@NotEmpty String name, @NotEmpty String lastname, @NotEmpty String phoneNumber,
			@NotEmpty @Email String mail, @NotEmpty String image, @NotEmpty String password, @NotEmpty Rol rol) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.image = image;
		this.password = password;
		this.rol = rol;
	}

}
