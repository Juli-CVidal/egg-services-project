package com.egg.services.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Size(min = 1, max = 5, message = "Please enter a valid score")
	private Double score;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer.id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier.id")
	private Supplier supplier;

	@NotEmpty
	private String content;

	@Column(nullable = false, columnDefinition = "MEDIUMTEXT")
	private String image;

	public Review(Customer customer, Supplier supplier,  String content, String image) {
		this.customer = customer;
		this.supplier = supplier;
		this.content = content;
		this.image = image;
	}

}

