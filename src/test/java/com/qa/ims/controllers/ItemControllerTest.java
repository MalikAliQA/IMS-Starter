package com.qa.ims.controllers;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)

public class ItemControllerTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private ItemDAO itemsDAO;
	
	@InjectMocks
	private ItemController controller;
	
	@Test
	public void testCreate() {
		 String name = "Valorant";
		 Double price = (double) 20;
		 Item created = new Item(name, price);

		
		Mockito.when(utils.getString()).thenReturn(name);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(itemsDAO.create(created)).thenReturn(created);
		
		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemsDAO, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1l, "Valorant", (double) 20));
		
		Mockito.when(itemsDAO.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		Mockito.verify(itemsDAO, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		Item updated = new Item(1l, "CSGO", 1.99);
		
		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(utils.getString()).thenReturn(updated.getItem_name());
		Mockito.when(utils.getDouble()).thenReturn(updated.getItem_price());
		Mockito.when(itemsDAO.update(updated)).thenReturn(updated);
		
		assertEquals(updated, controller.update());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemsDAO, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testDelete() {
		final long ID = 1l;
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(itemsDAO.delete(ID)).thenReturn(1);
		
		assertEquals(1L, controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemsDAO, Mockito.times(1)).delete(ID);	}

}
