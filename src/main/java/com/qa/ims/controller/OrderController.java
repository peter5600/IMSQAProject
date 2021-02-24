package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderLineDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLines;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	private OrderDAO OrderDAO;
	private OrderLineDAO OrderLineDAO;
	private Utils utils;// has a scanner and getString can be used
	public static final Logger Logger = LogManager.getLogger();

	public OrderController(OrderDAO OrderDAO, OrderLineDAO OrderLineDAO, Utils Utils) {
		this.OrderDAO = OrderDAO;
		this.OrderLineDAO = OrderLineDAO;
		this.utils = Utils;
	}
	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create() {
		Logger.info("What is the id of the customer the order is for");
		Long CustomerID = utils.getLong();
		Order Order = OrderDAO.create(new Order(CustomerID, 1l));//1 is the current userid
		Long OrderID = Order.getOrderID();
		String Input ="";
		while(!Input.equals("finish")){
			Logger.info("What is the id of the item that you would like to add to the order?");
			Long ItemID = utils.getLong();
			Logger.info("How many items should be included in the order?");
			Long Quantity = utils.getLong();
			Logger.info("If you would like to add another item to the system please enter yes, if not please enter finish");
			Input = utils.getString();
			OrderLineDAO.create(new OrderLines(OrderID, ItemID, Quantity));
		}
		return Order;
		
	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
