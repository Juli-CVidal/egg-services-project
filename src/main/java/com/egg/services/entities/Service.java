package com.egg.services.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;

import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private Boolean state;

	@NotEmpty
	private String serviceType;

	@NotEmpty
	private String description;
	
	public Service() {
		this.state = true;
	}

	public Service( @NotEmpty String serviceType, @NotEmpty String description) {
		super();
		this.state = true;
		this.serviceType = serviceType;
		this.description = description;
	}

	
}
