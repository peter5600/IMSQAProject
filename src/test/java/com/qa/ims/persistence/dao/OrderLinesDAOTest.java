package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLines;
import com.qa.ims.utils.DBUtils;

public class OrderLinesDAOTest {

	private OrderDAO DAO = new OrderDAO();
	private OrderLineDAO OrderLinesDAO = new OrderLineDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	
	@Test 
	public void ReadAllChildrenOrders() {

		List<Order> Orders = DAO.readAll();
		List<OrderLines> OrderLines = OrderLinesDAO.ReadAllOrdersBelongingToOrderID(Orders.get(0).getOrderID());
		OrderLines ExpectedOrderLine = new OrderLines(1l,1l,1l,5l);
		assertEquals(Orders.get(0).getOrderID(), OrderLines.get(0).getOrdersID());
		assertEquals(ExpectedOrderLine.getItemID(), OrderLines.get(0).getItemID());
		assertEquals(ExpectedOrderLine.getQuantity(), OrderLines.get(0).getQuantity());
	}
	
	@Test
	public void DeleteOrderLineOrders() {
		assertEquals(1, OrderLinesDAO.DeleteOrderLinesUsingOrderID(1l));//should be deleting the first record and returning one
	}
	
	@Test
	public void DeleteAnOrderLineFromAnOrder() {//selects an order of id 1 and an item with id 1 and deletes it only exists once
		//pass an item and an order
		assertEquals(1, OrderLinesDAO.DeleteOrderLinesUsingItemID(1l, 1l));
	}
	
	@Test
	public void AddAnitemToAnOrder() {//selects an order of id 1 and an item with id 1 and deletes it only exists once
		//pass an item and an order
		OrderLines OrderLine = new OrderLines(1l,1l,1l);//orders item quantity
		OrderLines ReturnedValue = OrderLinesDAO.AddItemsToOrderLines(1l, 1l, 1l);
		assertEquals(OrderLine.getOrdersID(), ReturnedValue.getOrdersID());//orders item quantity
		assertEquals(OrderLine.getItemID(), ReturnedValue.getItemID());//orders item quantity
		assertEquals(OrderLine.getQuantity(), ReturnedValue.getQuantity());//orders item quantity
	}
}
