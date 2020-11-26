package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final ItemDAO itemDAO = new ItemDAO();
	private final OrderDAO orderDAO = new OrderDAO(itemDAO);
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root", "testims");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order order = new Order(1L);
		final Order expected = new Order(3l, 1l);
		assertEquals(expected, orderDAO.create(order));
		
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l ,"Call Of Duty: B03", 44.99));
		items.add(new Item(1l ,"Overwatch", 19.99));
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1l, 1l, items));
		//expected.add(new Order(2l, 2l, items));
		List<Item> itemsTwo = new ArrayList<>();
		itemsTwo.add(new Item(2l ,"Call Of Duty: B03", 44.99));
		List<Order> expectedTwo = new ArrayList<>();
		expected.add(new Order(2l, 2l, itemsTwo));
		//expected.add(new Order(2l, 1l));
		
		//orderDAO.create(new Order(2l, 1l));
		assertEquals(expected, orderDAO.readAll());
		//assertEquals(expectedTwo, orderDAO.readAll());
		}
	
	@Test
	public void testReadLatest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l ,"Call Of Duty: B03", 44.99));
		Order expected = new Order(2l, 2l, items);
		assertEquals(expected, orderDAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Order update = new Order(1l, 2l);
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l ,"Call Of Duty: B03", 44.99));
		items.add(new Item(1l ,"Overwatch", 19.99));
		Order expected = new Order(1l, 2l, items);
		assertEquals(expected, orderDAO.update(update));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, orderDAO.delete(1));
	}
	
	@Test
	public void testRemoveItem() {
		assertEquals(1, orderDAO.removeItem(1, 1));
	}
	
	@Test
	public void deleteOrderItem() {
		assertEquals(2, orderDAO.deleteOrderItem(1));
	}

}
