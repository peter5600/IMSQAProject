package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrderLineDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLines;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	private OrderDAO OrderDAO;
	private OrderLineDAO OrderLineDAO;
	private ItemsDAO ItemsDAO;
	private Utils utils;// has a scanner and getString can be used
	public static final Logger Logger = LogManager.getLogger();

	public OrderController(OrderDAO OrderDAO, OrderLineDAO OrderLineDAO, ItemsDAO ItemsDAO, Utils Utils) {
		this.OrderDAO = OrderDAO;
		this.OrderLineDAO = OrderLineDAO;
		this.ItemsDAO = ItemsDAO;
		this.utils = Utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> Orders = OrderDAO.readAll();
		for (Order O : Orders) {
			Long OrderID = O.getOrderID();
			List<OrderLines> OrderLines = OrderLineDAO.ReadAllOrdersBelongingToOrderID(OrderID);
			Logger.info("-----------------------------------\nORDER-> " + O.toString());
			for (OrderLines OrderLine : OrderLines) {// here
				Logger.info("Individual Items -> " + OrderLine.toString());
			}
		}
		return Orders;
	}

	@Override
	public Order create() {
		Logger.info("What is the id of the customer the order is for");
		Long CustomerID = utils.getLong();
		Order OrderToCreate = new Order(CustomerID);
		Order Order = OrderDAO.create(OrderToCreate);// 1 is the current userid 
		Long OrderID = Order.getOrderID();
		String Input = "";
		while (!Input.equals("finish")) {
			Logger.info("What is the id of the item that you would like to add to the order?");
			Long ItemID = utils.getLong();
			Logger.info("How many items should be included in the order?");
			Long Quantity = utils.getLong();
			Logger.info(
					"If you would like to add another item to the system please enter yes, if not please enter finish");
			Input = utils.getString();
			OrderLineDAO.create(new OrderLines(OrderID, ItemID, Quantity));
		}
		return Order;

	}

	@Override
	public Order update() {//no updates its two seperate functions
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		Logger.info("What is the id of the order that you would like to delete");
		Long OrderID = utils.getLong();
		int ChildrenDeletedRecordsCount = OrderLineDAO.DeleteOrderLinesUsingOrderID(OrderID);
		int DeletedRecordsCount = OrderDAO.delete(OrderID);
		Logger.info(String.format(
				"%x order records were deleted from the system and %x items were deleted from those orders.",
				DeletedRecordsCount, ChildrenDeletedRecordsCount));
		return DeletedRecordsCount;
	}

	public float CalculateCost() {
		float Cost = 0f;
		Logger.info("What is the id of the order that you would like to know the cost of?");
		Long OrderID = utils.getLong();
		List<OrderLines> OrderLines = OrderLineDAO.ReadAllOrdersBelongingToOrderID(OrderID);
		for (OrderLines OrderLine : OrderLines) {
			Long ItemID = OrderLine.getItemID();
			Items Item = ItemsDAO.read(ItemID);
			Cost += Item.getCost() * OrderLine.getQuantity();
		}
		Logger.info(String.format("The total cost of the order with the ID: %x is $%s", OrderID,
				String.format("%.02f", Cost)));
		// get all children records and get the items id from that and add the price
		return Cost;
	}

	public int DeleteItemFromOrder() {
		Logger.info("What is the ID of the order that you want to delete an item from");
		Long OrderID = utils.getLong();
		Logger.info("What is the id of the item that you would like to remove. If this item exists multiple times in an order all of the orders for that item will be removed.");
		Long ItemID = utils.getLong();
		int RecordsDeleted = OrderLineDAO.DeleteOrderLinesUsingItemID(ItemID, OrderID);
		Logger.info(String.format("Item ID: %d was removed from Order ID: %d %d many times", ItemID, OrderID, RecordsDeleted));
		return RecordsDeleted;
	}
	
	public OrderLines AddItemToOrder() {
		Logger.info("What is the ID of the order that you want to delete an item from");
		Long OrderID = utils.getLong();
		Logger.info("What is the id of the item that you would like to add to the order");
		Long ItemID = utils.getLong();
		Logger.info("How many items should be added to the order");
		Long Quantity = utils.getLong();
		OrderLines OrderAdded = OrderLineDAO.AddItemsToOrderLines(OrderID, ItemID, Quantity);
		Logger.info("Item added to the order");
		return OrderAdded;
	}

}
