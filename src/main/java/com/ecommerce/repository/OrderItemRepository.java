package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrders(Orders order);
}
