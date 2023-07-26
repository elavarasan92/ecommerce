package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderItemRequest;
import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
	List<OrderItem> getAllOrderItems();
	List<OrderItem> findByOrder(Orders order);
	OrderItem getOrderItemById(Long id);
	OrderItem saveOrderItem(OrderItemRequest orderItemRequest);
	OrderItem updateOrderItem(OrderItemRequest orderItemRequest,Long id);
	void deleteOrderItemById(Long id);
	List<OrderItem> getOrderItemsByOrderId(long orderId) ;
}
