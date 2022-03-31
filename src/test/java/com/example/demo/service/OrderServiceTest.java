package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Exception.NotEnoughStockException;
import com.example.demo.domain.Address;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {
	
	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	@Test
	public void 상품주문() throws Exception {
		//given
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강북", "123-123"));
		em.persist(member);
		
		Book book = new Book();
//		book.setName("시골 jpa");
//		book.setPrice(10000);
//		book.setStockQuantity(10);
		em.persist(book);
		
		int orderCount = 2;
		
		//when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("상품 주문시 상태는 order", OrderStatus.ORDER,
				getOrder.getStatus());
		
		assertEquals("주문한 상품 종류 수가 정확해야 함", 1, getOrder.getOrderItems().size());

	}
	
//	@Test
//	public void 상품주문_재고수량초과() throws Exception {
//		//given
//		Member member = new Member();
//		member.setName("회원1");
//		member.setAddress(new Address("서울", "강북", "123-123"));
//		em.persist(member);
//		
//		Item book = new Item();
//		em.persist(book);
//		
//		int orderCount = 10;
//		
//		
//		//when
//		orderService.order(member.getId(), item.getId(), orderCount);
//		
//		//then
//		fail("예외발생해야한다.");
//	}
	

}
