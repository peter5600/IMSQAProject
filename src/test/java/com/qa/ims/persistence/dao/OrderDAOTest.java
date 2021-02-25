package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void CreateOrder() {//the ids are being returned 
		Order ExpectedOrder = new Order(2l,1l,1l);//id is being returned in hexadecimal
		Order Created = DAO.create(ExpectedOrder);
		assertEquals(ExpectedOrder.getOrderID(), Created.getOrderID());
		assertEquals(ExpectedOrder.getCustomerID(), Created.getCustomerID());
		assertEquals(ExpectedOrder.getUserID(), Created.getUserID());
	}//maybe test orderlines create as well but its just an extension of order create
	
	@Test
	public void ReadAllOrder() {//For reading the orders and the OrderLines attaches to the orders
		//order is 1,1,
		//orderlines is 1,1,5
		Order ExpectedOrder = new Order(1l,1l,1l);
		//(Long ID, Long OrdersID, Long ItemID, Long Quantity)
		List<Order> Orders = DAO.readAll();
		assertEquals(ExpectedOrder.getCustomerID(), Orders.get(0).getCustomerID());
		assertEquals(ExpectedOrder.getUserID(), Orders.get(0).getUserID()); 
	}
	
	@Test
	public void ReadLatest() {
		Order ExpectedOrder = new Order(1l,1l,1l);
		Order ActualOrder = DAO.ReadLatest();
		assertEquals(ExpectedOrder.getCustomerID(), ActualOrder.getCustomerID());
		assertEquals(ExpectedOrder.getUserID(), ActualOrder.getUserID());
	}
	
	@Test
	public void ReadID() {
		Order ExpectedOrder = new Order(1l,1l,1l);
		Order ActualOrder = DAO.read(1l);
		assertEquals(ExpectedOrder.getCustomerID(), ActualOrder.getCustomerID());
		assertEquals(ExpectedOrder.getUserID(), ActualOrder.getUserID());
	}
	
	@Test
	public void DeleteOrder() {//when order not working is fixed ill handle this
		assertEquals(1, DAO.delete(1l));//should be deleting the first record and returning one
	}
}
