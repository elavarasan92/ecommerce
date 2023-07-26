package com.ecommerce.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import com.ecommerce.dto.OrderItemRequest;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.service.OrderItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderItemController.class)
@ExtendWith(SpringExtension.class)
public class OrderItemControllerTest {
	private OrderItem orderItem;
	@PostConstruct
	private void init() {
		orderItem = new OrderItem();
		orderItem.setId(1L);
		orderItem.setName("Mobile");
		orderItem.setPrice(BigDecimal.valueOf(12000.00));
	}
	@Autowired
	MockMvc mockMvc;
	@MockBean
	OrderItemServiceImpl orderItemService;
	@Test
	void getAllOrderItemsTest() throws Exception {
		given(orderItemService.getAllOrderItems())
				.willReturn(List.of(orderItem));
		mockMvc.perform(get("/api/v0/order-item"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[*].name").value("Mobile"));
		verify(orderItemService).getAllOrderItems();
	}
	@Test
	void getOrderItemsByOrderIdTest() throws Exception {
		given(orderItemService.getOrderItemsByOrderId(1L))
				.willReturn(List.of(orderItem));
		mockMvc.perform(get("/api/v0/order-item/order/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[*].name").value("Mobile"));
		verify(orderItemService).getOrderItemsByOrderId(1L);
	}

	@Test
	void getOrderItemByIdTest() throws Exception {
		given(orderItemService.getOrderItemById(1L))
				.willReturn(orderItem);
		mockMvc.perform(get("/api/v0/order-item/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Mobile"));
		verify(orderItemService).getOrderItemById(1L);
	}
	@Test
	void createOrderItemForOrderTest() throws Exception {
		given(orderItemService.saveOrderItemForOrder(anyLong(),any(OrderItemRequest.class))).willReturn(orderItem);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v0/order-item/order/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"name\": \"Mobile\",\"price\": 12000.00 }"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Mobile"));
	}
	@Test
	void updateOrderItemTest() throws Exception {
		orderItem.setName("Camera");
		given(orderItemService.updateOrderItem(any(OrderItemRequest.class),anyLong())).willReturn(orderItem);
		mockMvc.perform(MockMvcRequestBuilders
						.put("/api/v0/order-item/3")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"name\": \"Camera\" }"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Camera"));
	}
	@Test
	void deleteOrderItemTest() throws Exception {
		mockMvc.perform(delete("/api/v0/order-item/1"))
				.andExpect(status().isOk());
	}
}