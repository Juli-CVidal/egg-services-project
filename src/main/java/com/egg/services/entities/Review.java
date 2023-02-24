package com.egg.services.entities;

import java.util.List;

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

	public Review(Customer customer, Supplier supplier, @NotEmpty String content, String image) {
		this.customer = customer;
		this.supplier = supplier;
		this.content = content;
		this.image = image;
	}

}

