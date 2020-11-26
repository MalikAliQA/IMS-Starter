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
		//LOGGER.info("CREATED ORDER SELECT UPDATE TO ADD ITEMS TO ORDER");
		String end;
		do {
			List<Item> items = itemDAO.readAll();
			for (Item item : items) {
				LOGGER.info(item.toString());
			}
			LOGGER.info("SELECT ID OF ITEM TO ADD");
			long itemID = utils.getLong();
			orderDAO.createOrderItem(order.getId(), itemID);
			LOGGER.info("ADD MORE: YES / NO");
			end = utils.getString();
		}while (end.toLowerCase().equals("yes"));
		order = orderDAO.readLatest();
		return order;
	}
		
		

	@Override
	public Order update() {
		Order order;
		Long id = null;
		Long itemID;
		boolean flag = true;
		while(flag) {
			LOGGER.info("\n 1 Add an Item to Order \n 2 Delete an Item \n 3 Return to Main Menu");
			String choice = utils.getString();
			switch(choice) {
			case "1":
				//itemDAO.readAll();
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
			//itemDAO.readAll();
			List<Order> ordersdel = orderDAO.readAll();
			for (Order order1 : ordersdel) {
				LOGGER.info(order1.toString());
			}
			LOGGER.info("SELECT ORDER ID TO DELETE FROM");
			id = utils.getLong();
			LOGGER.info(orderDAO.readOrderItems(id));
			LOGGER.info("SELECT ID OF ITEM TO DELETE FROM ORDER");
			Long itemID1 = utils.getLong();
			orderDAO.removeItem(id, itemID1);
			break;
			
			case "3":
				LOGGER.info("Returning to ORDER MENU \n");
				flag = false;
				break;
			}
		}
		return orderDAO.readOrder(id);
	}

	@Override
	public int delete() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		LOGGER.info("SELECT ID OF ORDER TO DELETE");
		Long id = utils.getLong();
		orderDAO.deleteOrderItem(id);
		return orderDAO.delete(id);
		
	}

}
