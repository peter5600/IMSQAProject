package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAOTest {

	private ItemsDAO ItemsDAO = new ItemsDAO();// use this for testing use as i would in the program

	@Before
	public void setup() {// init the db before the tests start
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void create() {// This test has be written for usage later it can't be used yet because
		// some functions required haven't been implemented and can't be until i finish
		// the items epic on jira
		Items TestItem = new Items(2l, "Pepsi", 3.75f);// 2 because 1 already exists
		Items ExpectedItem = ItemsDAO.create(TestItem);
		assertEquals(TestItem.getName(), ExpectedItem.getName());// will return null because read latest hasn't been
																	// implemnted
		assertEquals(TestItem.getCost(), ExpectedItem.getCost(), 0.0002);
		assertEquals(TestItem.getID(), ExpectedItem.getID());
	}

	@Test
	public void ReadAllTest() {
		List<Items> TestValues = new ArrayList<>();
		TestValues.add(new Items(1l, "Pepsi", 3.50f));

		List<Items> Expected = ItemsDAO.readAll();
		// assertEquals(TestValues, Expected);//compareing like this leads to the exact
		// same values but not being the same
		assertEquals(TestValues.get(0).getID(), Expected.get(0).getID());
		assertEquals(TestValues.get(0).getName(), Expected.get(0).getName());
		assertEquals(TestValues.get(0).getCost(), Expected.get(0).getCost(), 0.0002);// delta
	}

	@Test
	public void ReadLatestTest() {
		Items TestValue = new Items(1l, "Pepsi", 3.50f);

		Items Expected = ItemsDAO.readLatest();
		assertEquals(TestValue.getID(), Expected.getID());
		assertEquals(TestValue.getName(), Expected.getName());
		assertEquals(TestValue.getCost(), Expected.getCost(), 0.0002);// delta
		// i think because of float i can't just compare them i have to specify a delate
		// so do them all individualy
	}

	@Test
	public void ReadWithIDTest() {
		Items TestValue = new Items(1l, "Pepsi", 3.50f);

		Items Expected = ItemsDAO.read(1l);
		assertEquals(TestValue.getID(), Expected.getID());
		assertEquals(TestValue.getName(), Expected.getName());
		assertEquals(TestValue.getCost(), Expected.getCost(), 0.0002);// delta
	}

	@Test
	public void UpdateItemTest() {
		// 1l is pepsi 3.50f
		Items ExpectedItemBack = new Items(1l, "Lemonade", 4.00f);

		Items ReturnedItem = ItemsDAO.update(ExpectedItemBack);
		assertEquals(ExpectedItemBack.getName(), ReturnedItem.getName());
		assertEquals(ExpectedItemBack.getCost(), ReturnedItem.getCost(), 0.0002);
		assertEquals(ExpectedItemBack.getID(), ReturnedItem.getID());
	}
	
	@Test
	public void DeleteTest() {
		assertEquals(1,ItemsDAO.delete(1));
	}
}
