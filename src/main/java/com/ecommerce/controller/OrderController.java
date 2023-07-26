package com.ecommerce.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entity.Orders;
import com.ecommerce.service.OrderService;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@GetMapping
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> orders = orderService.getAllOrders();
		if (CollectionUtils.isEmpty(orders))
			throw new NoSuchElementException("No orders exist");
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable long id) {
		return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {
		orderService.getOrderById(id);
		orderService.deleteOrderById(id);
		return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest) {
		return new ResponseEntity<>(orderService.saveOrder(orderRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Orders> update(@RequestBody OrderRequest orderRequest, @PathVariable long id) {
		return new ResponseEntity<>(orderService.updateOrder(orderRequest, id), HttpStatus.OK);
	}
}
