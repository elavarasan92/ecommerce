package com.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.dto.OrderItemRequest;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Orders;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{
	private final ModelMapper modelMapper;
	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;

	@Override
	public List<OrderItem> getAllOrderItems() {
		return orderItemRepository.findAll();
	}
	@Override
	public List<OrderItem> getOrderItemsByOrderId(long orderId) {
		return orderItemRepository.findByOrders(orderRepository.getReferenceById(orderId));
	}
	@Override
	public List<OrderItem> findByOrder(Orders order) {
		return orderItemRepository.findByOrders(order);
	}

	@Override
	public OrderItem getOrderItemById(Long id) {
		return getOrder(id);
	}

	@Override
	public OrderItem saveOrderItem(OrderItemRequest orderItemRequest) {
		return orderItemRepository.save(modelMapper.map(orderItemRequest,OrderItem.class));
	}

	@Override
	public OrderItem updateOrderItem(OrderItemRequest orderItemRequest, Long id) {
		OrderItem destination = getOrder(id);
		modelMapper.map(orderItemRequest,destination);
		return orderItemRepository.save(destination);
	}

	@Override
	public void deleteOrderItemById(Long id) {
		orderItemRepository.deleteById(id);
	}

	private OrderItem getOrder(Long id) {
		return orderItemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No OrderItem exist with ID = " + id));
	}
}
