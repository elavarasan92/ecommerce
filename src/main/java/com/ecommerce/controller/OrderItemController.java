package com.ecommerce.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.dto.OrderItemRequest;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Orders;
import com.ecommerce.service.OrderItemService;
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
@RequestMapping("/api/v0/order-item")
@RequiredArgsConstructor
public class OrderItemController {
	private final OrderItemService orderItemService;
	@GetMapping
	public ResponseEntity<List<OrderItem>> getAllOrderItems() {
		List<OrderItem> orderItems = orderItemService.getAllOrderItems();
		if (CollectionUtils.isEmpty(orderItems))
			throw new NoSuchElementException("No order item exist");
		return new ResponseEntity<>(orderItems, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderItem> getOrderItemById(@PathVariable long id) {
		return new ResponseEntity<>(orderItemService.getOrderItemById(id), HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<OrderItem> update(@RequestBody OrderItemRequest orderItemRequest, @PathVariable long id) {
		return new ResponseEntity<>(orderItemService.updateOrderItem(orderItemRequest, id), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrderItemById(@PathVariable("id") long id) {
		orderItemService.getOrderItemById(id);
		orderItemService.deleteOrderItemById(id);
		return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
	}
	@GetMapping("/order/{orderId}")
	public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable long orderId) {
		List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderItems))
			throw new NoSuchElementException("No order item exist for this order id "+orderId);
		return new ResponseEntity<>(orderItems, HttpStatus.OK);
	}

	@PostMapping("/order/{orderId}")
	public ResponseEntity<OrderItem> createOrderItemForOrder(@PathVariable long orderId,
			@RequestBody OrderItemRequest orderItemRequest) {
		return new ResponseEntity<>(orderItemService.saveOrderItemForOrder(orderId,orderItemRequest), HttpStatus.CREATED);
	}





}
