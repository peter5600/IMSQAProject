package com.qa.ims.persistence.domain;


/*
 * Process
 * User needs to be able to view orders, delete orders and items to orders, calculate cost of orders and delete an item from an order
 * How orders work is when an order is created get custid and generate an orders id based of order id then for every 
 * item and quantity make a new record in OrderLines and that will be associated with the order
 * OrdersID has been removed since it isn't needed
 */
public class Order {
	private Long OrderID;
	private Long CustomerID;
	private Long UserID;
	
	public Order(Long CustomerID, Long UserID) {
		this.setCustomerID(CustomerID);
		this.setUserID(UserID);
	}
	
	public Order(Long OrderID, Long CustomerID, Long UserID) {
		this(CustomerID, UserID);
		this.setOrderID(OrderID);
	}
	
	
	public Long getOrderID() {
		return OrderID;
	}
	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}
	public Long getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(Long customerID) {
		CustomerID = customerID;
	}
	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
}
