package com.qa.ims.persistence.dao;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOFailTest {
	
	private final ItemDAO itemDAO = new ItemDAO();
	private final OrderDAO orderDAO = new OrderDAO(itemDAO);

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "wrongpass", "testims");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order order = new Order(1L);
		assertEquals(null, orderDAO.create(order));
	}
	
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(3l ,"Valorant", (double) 20));
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1l, 1l, items));
		expected.add(new Order(2l, 1l));
		
		orderDAO.create(new Order(2l, 1l));
		assertEquals(new ArrayList<>(), orderDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l ,"Call Of Duty: B03", 44.99));
		assertEquals(null, orderDAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Order update = new Order(1l, 1l);
		List<Item> items = new ArrayList<>();
		items.add(new Item(1l ,"Overwatch", 19.99));
		assertEquals(null, orderDAO.update(update));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, orderDAO.delete(1));
	}
	
	@Test
	public void testRemoveItem() {
		assertEquals(0, orderDAO.removeItem(1, 1));
	}


}
