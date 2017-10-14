package com.demo.dao.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Price {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer price;
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
}
