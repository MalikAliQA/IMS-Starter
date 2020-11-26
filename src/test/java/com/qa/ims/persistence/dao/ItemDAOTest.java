package com.qa.ims.persistence.dao;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO itemDAO = new ItemDAO();
	
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
	Item created = new Item("Valorant", (double) 20);
	Item expected = new Item(3L, "Valorant", (double) 20);
	
	assertEquals(expected, itemDAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1l,"Overwatch", 19.99));
		expected.add(new Item(2l, "Call Of Duty: B03", 44.99));
		
		assertEquals(expected, itemDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		Item expected = new Item(2l, "Call Of Duty: B03", 44.99);
		
		assertEquals(expected, itemDAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Item expected = new Item(1l, "CSGO", (double) 1.99);
		
		assertEquals(expected, itemDAO.update(expected));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, itemDAO.delete(1));
	}

}
