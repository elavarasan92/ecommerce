package com.ecommerce.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long orderNumber;
	private boolean deleted;
	@OneToMany(mappedBy = "orders", targetEntity = OrderItem.class, fetch = FetchType.LAZY)
	private Set<OrderItem> orderItems;
}
