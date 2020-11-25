package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {


	private long id;
	private long customerID;
	private Long item_id;
	private Long order_id;
	private List<Item> items = new ArrayList<>();
	

	public Order(long customerID) {
		this.customerID = customerID;
	}
	
	
	
	public Order(long item_id, long order_id) {
		super();
		this.item_id = item_id;
		this.order_id = order_id;
	}
	
	public Order(long customerID, long item_id, long order_id) {
		super();
		this.customerID = customerID;
		this.item_id = item_id;
	}

	public Order(long id, long customerID, List<Item> items) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.items = items;
	}
	//Getters and Setters
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getCustomerID() {
		return customerID;
	}


	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}


	public Long getItem_id() {
		return item_id;
	}


	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	
	public Long getOrder_id() {
		return order_id;
	}



	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	

	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerID != other.customerID)
			return false;
		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}


	@Override
	public String toString() {
		String eachprice = "";
		double totalPrice = 0.0;
		for(Item item: items) {
			totalPrice += item.getItem_price();
			eachprice += item.toString(); //+ "\n";
		}
		
		return "================================================================================\n"
				+ "Order ID: " + id + " | Customer ID: " + customerID + "\nItems: \n" + eachprice + "\n| Total Price £" + totalPrice + " |\n"
				+ "================================================================================\n";
	}

	
}