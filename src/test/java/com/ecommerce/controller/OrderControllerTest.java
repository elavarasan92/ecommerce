package com.ecommerce.controller;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entity.Orders;
import com.ecommerce.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import javax.annotation.PostConstruct;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {
	private Orders order;
	@PostConstruct
	private void init() {
		order = new Orders();
		order.setId(1L);
		order.setOrderNumber(3);
	}
	@Autowired
	MockMvc mockMvc;
	@MockBean
	OrderServiceImpl orderService;
	@Test
	void getAllOrdersTest() throws Exception {
		given(orderService.getAllOrders())
				.willReturn(List.of(order));
		mockMvc.perform(get("/api/v0/orders"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[*].orderNumber").value(3));
		verify(orderService).getAllOrders();
	}
	@Test
	void getOrderByIdTest() throws Exception {
		given(orderService.getOrderById(1L))
				.willReturn(order);
		mockMvc.perform(get("/api/v0/orders/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orderNumber").value(3));
		verify(orderService).getOrderById(1L);
	}
	@Test
	void createOrderTest() throws Exception {
		given(orderService.saveOrder(any(OrderRequest.class))).willReturn(order);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v0/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"orderNumber\": 3 }"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.orderNumber").value(3));
	}
	@Test
	void updateOrderTest() throws Exception {
		order.setOrderNumber(4);
		given(orderService.updateOrder(any(OrderRequest.class),any())).willReturn(order);
		mockMvc.perform(MockMvcRequestBuilders
						.put("/api/v0/orders/3")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"orderNumber\": 4 }"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orderNumber").value(4));
	}
	@Test
	void deleteOrderTest() throws Exception {
		mockMvc.perform(delete("/api/v0/orders/1"))
				.andExpect(status().isOk());
	}
}