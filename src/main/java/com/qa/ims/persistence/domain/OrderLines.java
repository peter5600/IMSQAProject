package com.qa.ims.persistence.domain;

public class OrderLines {
	private Long id;
	private Long OrdersID;
	private Long ItemID;
	private Long Quantity;
	
	public OrderLines(Long OrdersID, Long ItemID, Long Quantity) {
		this.setOrdersID(OrdersID);
		this.setItemID(ItemID);
		this.setQuantity(Quantity);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrdersID() {
		return OrdersID;
	}
	public void setOrdersID(Long ordersID) {
		OrdersID = ordersID;
	}
	public Long getItemID() {
		return ItemID;
	}
	public void setItemID(Long itemID) {
		ItemID = itemID;
	}
	public Long getQuantity() {
		return Quantity;
	}
	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}
}