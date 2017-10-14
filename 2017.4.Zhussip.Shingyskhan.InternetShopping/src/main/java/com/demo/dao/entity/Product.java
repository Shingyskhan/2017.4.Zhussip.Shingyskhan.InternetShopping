package com.demo.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Integer quantity;
	private String description;
	private Integer price;
	/*@OneToMany(targetEntity=Price.class,mappedBy="product",cascade=CascadeType.ALL)
	private List<Price>prices;*/
	
}
