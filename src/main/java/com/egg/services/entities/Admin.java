package com.egg.services.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin extends Person {

	public Admin() {
		super();
		rol = Rol.ADMIN;
	}

	public Admin(String name, String lastname, String phoneNumber, String mail, String image, String password) {
		super(name, lastname, phoneNumber, mail, image, password, Rol.ADMIN);
	}

}
