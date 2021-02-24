package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

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
	public void CreateControllerTest() {
		Items Item = new Items("Coke", 2.50f);
		assertEquals(Item, ItemsDAO.create(Item));//will also return null because readlatest hasn't been implemented
	}
}
