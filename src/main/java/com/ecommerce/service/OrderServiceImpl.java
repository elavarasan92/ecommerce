package com.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entity.Orders;
import com.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	private final ModelMapper modelMapper;
	private final OrderRepository orderRepository;

	@Override
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Orders getOrderById(Long id) {
		return getOrder(id);
	}

	@Override
	public Orders saveOrder(OrderRequest orderRequest) {
		return orderRepository.save(modelMapper.map(orderRequest,Orders.class));
	}

	@Override
	public Orders updateOrder(OrderRequest orderRequest, Long id) {
		Orders destination = getOrder(id);
		modelMapper.map(orderRequest,destination);
		return orderRepository.save(destination);
	}

	@Override
	public void deleteOrderById(Long id) {
		orderRepository.deleteById(id);
	}

	private Orders getOrder(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Orders exist with ID = " + id));
	}
}
