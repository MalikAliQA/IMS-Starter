package com.qa.ims.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;



public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	private ItemDAO itemDAO;
	private CustomerDAO custDAO;
		
	public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO, CustomerDAO custDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.itemDAO = itemDAO;
		this.custDAO = custDAO;
	}

	@Override
	public List<Order> readAll(){
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}
	


	@Override
	public Order create() {
		List<Customer> customers = custDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer.toString());
		}
		LOGGER.info("SELECT CUSTOMER ID ");
		Long customerID = utils.getLong();
		Order order = orderDAO.create(new Order(customerID));
		LOGGER.info("CREATED ORDER SELECT UPDATE TO ADD ITEMS TO ORDER");
		return order;
	}
		
		

	@Override
	public Order update() {
		Order order;
		Long id = null;
		Long itemID;
		boolean flag = true;
		while(flag) {
			LOGGER.info("\n 1 Add an Item to Order \n 2 Return to Main Menu");
			String choice = utils.getString();
			switch(choice) {
			case "1":
				itemDAO.readAll();
				List<Order> orders = orderDAO.readAll();
				for (Order order1 : orders) {
					LOGGER.info(order1.toString());
				}
				LOGGER.info("SELECT ORDER ID TO ADD ITEMS TO");
				id = utils.getLong();
				List<Item> items = itemDAO.readAll();
				for (Item item : items) {
				LOGGER.info(item.toString());
				}
				LOGGER.info("SELECT ITEM ID");
				itemID = utils.getLong();
				orderDAO.createOrderItem(id, itemID);
				break;
			case "2":
				LOGGER.info("Returning to ORDER MENU \n");
				flag = false;
				order = orderDAO.readOrder(id);
				return order;
			}
		}
		return null;
	}

	@Override
	public int delete() {
		boolean flag = true;
		while (flag) {
			LOGGER.info("\n 1 REMOVE AN ITEM FROM ORDER \n 2 DELETE FULL ORDER \n 3 RETURN TO ORDER MENU");
			String choice = utils.getString();
			switch(choice) {
			case "1":
				itemDAO.readAll();
				List<Order> orders = orderDAO.readAll();
				for (Order order1 : orders) {
					LOGGER.info(order1.toString());
				}
				LOGGER.info("SELECT ORDER ID TO DELETE FROM");
				Long id = utils.getLong();
				LOGGER.info(orderDAO.readOrderItems(id));
				LOGGER.info("SELECT ID OF ITEM TO DELETE FROM ORDER");
				Long itemID = utils.getLong();
				orderDAO.removeItem(id, itemID);
				break;
			case "2":
				List<Order> orders1 = orderDAO.readAll();
				for (Order order2 : orders1) {
					LOGGER.info(order2.toString());
				}
				LOGGER.info("ENTER ID OF ORDER TO DELETE");
				Long order_id = utils.getLong();
				orderDAO.delete(order_id);
				break;
			case "3":
				LOGGER.info("Returning to ORDER MENU \n");
				flag = false;
				break;	
			}
		}
		return 0;
	}

}
