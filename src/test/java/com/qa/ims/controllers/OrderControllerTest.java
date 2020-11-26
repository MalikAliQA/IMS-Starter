package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)

public class OrderControllerTest {
	
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private ItemDAO itemDAO;
	
	@Mock
	private CustomerDAO customerDAO;

	@InjectMocks
	private OrderController controller;
	
	@Test
	//for choice no
	public void testCreateno() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "jordan", "harrison"));
		List<Item> orderitems = new ArrayList<>(); 
		orderitems.add(new Item(1l, "Valorant", (double) 20));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(3l, 1l, orderitems));
		
	
		Long custID = 1l;
		Long itemid = 1l;
		Long orderid = 3l;
		String choiceno = "no";//, choiceyes = "yes";
		Order createdorder = new Order(custID);
		Order expectedorder = new Order(3l, custID, orderitems);
		Order createorderitem = new Order(orderid, itemid);
		Order exptectedorderitem = new Order (3l, itemid);
		
		
		
		Mockito.when(customerDAO.readAll()).thenReturn(customers);
		Mockito.when(utils.getLong()).thenReturn(custID, itemid);
		Mockito.when(orderDAO.create(createdorder)).thenReturn(expectedorder);
		Mockito.when(itemDAO.readAll()).thenReturn(orderitems);
		Mockito.when(utils.getLong()).thenReturn(itemid);
		//Mockito.when(orderDAO.createOrderItem(orderid, itemid)).thenReturn(exptectedorderitem);
		Mockito.when(utils.getString()).thenReturn(choiceno);//);, choiceyes);
		Mockito.when(orderDAO.readLatest()).thenReturn(orders.get(0));
		
		assertEquals(expectedorder, controller.create());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(customerDAO, Mockito.times(1)).readAll();
		Mockito.verify(orderDAO, Mockito.times(1)).create(createdorder);
		Mockito.verify(orderDAO, Mockito.times(1)).createOrderItem(anyLong(), anyLong());
		
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1l, "Valorant", (double) 20));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1l, 1l, items));
		
		
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		
		Mockito.verify(orderDAO, Mockito.times(1)).readAll();
		}
	
	@Test
	public void testUpdateone() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "jordan", "harrison"));
		List<Item> orderitems = new ArrayList<>(); 
		orderitems.add(new Item(1l, "Valorant", (double) 20));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(3l, 1l, orderitems));
		String choiceadd = "1";//, choicedel = "2", choicereturn = "3";
		Long orderid = 1l;
		Long itemdid = 1l;
		Long delorderid = 1l;
		Long delitemid = 1l;
		
		Order createorderitem = new Order(orderid, itemdid);
		Order exptectedorderitem = new Order (3l, itemdid);
		
		
		
		Mockito.when(utils.getString()).thenReturn(choiceadd, "3");
		//Mockito.when(itemDAO.readAll()).thenReturn(orderitems);
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		Mockito.when(utils.getLong()).thenReturn(orderid, itemdid);
		Mockito.when(itemDAO.readAll()).thenReturn(orderitems);
		//Mockito.when(utils.getLong()).thenReturn(itemdid);
		Mockito.when(orderDAO.createOrderItem(orderid, itemdid)).thenReturn(exptectedorderitem);
		Mockito.when(orderDAO.readOrder(anyLong())).thenReturn(exptectedorderitem);

		
		assertEquals(exptectedorderitem, controller.update());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(orderDAO, Mockito.times(1)).readAll();
		Mockito.verify(orderDAO, Mockito.times(1)).createOrderItem(anyLong(), anyLong());
		
	}
	
	@Test
	public void testUpdatetwo() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "jordan", "harrison"));
		List<Item> orderitems = new ArrayList<>(); 
		orderitems.add(new Item(1l, "Valorant", (double) 20));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(3l, 1l, orderitems));
		String choicedel = "2";
		Long orderid = 1l;
		Long itemdid = 1l;
		Long delorderid = 1l;
		Long delitemid = 1l;
		
		Order createorderitem = new Order(orderid, itemdid);
		Order exptectedorderitem = new Order (3l, itemdid);
		
		
		
		Mockito.when(utils.getString()).thenReturn(choicedel, "3");
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		Mockito.when(utils.getLong()).thenReturn(delorderid, delitemid);
		Mockito.when(orderDAO.readOrderItems(orderid)).thenReturn(orderitems);
		Mockito.when(orderDAO.removeItem(orderid, delitemid)).thenReturn(1);
		Mockito.when(orderDAO.readOrder(anyLong())).thenReturn(exptectedorderitem);

		
		assertEquals(exptectedorderitem, controller.update());
		//assertEquals(exptectedorderitem, controller.createOrderItem());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(orderDAO, Mockito.times(1)).readAll();
		Mockito.verify(orderDAO, Mockito.times(1)).removeItem(orderid, delitemid);
		
		

	}
	@Test
	public void testDelete() {
		
		List<Item> items = new ArrayList<>();
		items.add(new Item(1l, "Valorant", (double) 20));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1l, 1l, items));
		Long orderid = 1l;
		
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		Mockito.when(utils.getLong()).thenReturn(orderid);
		Mockito.when(orderDAO.deleteOrderItem(orderid)).thenReturn(1);
		Mockito.when(orderDAO.delete(orderid)).thenReturn(1);
		
		assertEquals(orders, controller.readAll());
		assertEquals(1L, controller.delete());
		//assertEquals(1L,  controller.deleteOrderItem());
		
		Mockito.verify(orderDAO, Mockito.times(2)).readAll();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).delete(orderid);
		Mockito.verify(orderDAO, Mockito.times(1)).deleteOrderItem(orderid);
		
	}

}
