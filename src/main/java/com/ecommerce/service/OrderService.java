package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entity.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
	List<Orders> getAllOrders();
	Orders getOrderById(Long id);
	Orders saveOrder(OrderRequest order);
	Orders updateOrder(OrderRequest order,Long id);
	void deleteOrderById(Long id);
}
