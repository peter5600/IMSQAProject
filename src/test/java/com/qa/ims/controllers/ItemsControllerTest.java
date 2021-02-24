package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class ItemsControllerTest {
	
	private ItemsDAO ItemsDAO = new ItemsDAO();//use this for testing use as i would in the program
	private Utils utils = new Utils();
	@Before
	public void setup() {//init the db before the tests start
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test 
	public void CreateControllerTest() {//we dont know mockito so ive got to use the dao instead of the controller
		Items Item = new Items(2l,"Coke", 2.50f);
		Items ReturnedValue = ItemsDAO.create(Item);
		assertEquals(Item.getName(), ReturnedValue.getName());
		assertEquals(Item.getCost(), ReturnedValue.getCost(), 0.0002);
		//You have to specify an acceptable delta this is teh diffrence between the floats that is ok
	}
	
	@Test 
	public void ReadAllTest() {
		//sql data creates a record 
		//Pepsi, 3.50f, 1
		ArrayList<Items> ItemList = new ArrayList<>();
		ItemList.add(new Items(1l,"Pepsi", 3.50f));
		
		List<Items> ValuesRead = ItemsDAO.readAll();
		assertEquals(ItemList.get(0).getName(), ValuesRead.get(0).getName());
		assertEquals(ItemList.get(0).getCost(), ValuesRead.get(0).getCost(), 0.0002);
	}
	
	@Test
	public void UpdateTest() {
		//1l is pepsi 3.50f
		Items ExpectedItemBack = new Items(1l,"Lemonade",4.00f);
		
		Items ReturnedItem = ItemsDAO.update(ExpectedItemBack);
		assertEquals(ExpectedItemBack.getName(), ReturnedItem.getName());
		assertEquals(ExpectedItemBack.getCost(), ReturnedItem.getCost(), 0.0002);
		assertEquals(ExpectedItemBack.getID(), ReturnedItem.getID());
	}
}
