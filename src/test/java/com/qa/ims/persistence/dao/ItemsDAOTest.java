package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAOTest {

	private ItemsDAO ItemsDAO = new ItemsDAO();//use this for testing use as i would in the program
	@Before
	public void setup() {//init the db before the tests start
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	} 
	
	@Test
	public void create() {//This test has be written for usage later it can't be used yet because 
		//some functions required haven't been implemented and can't be until i finish the items epic on jira
		Items TestItem = new Items("Pepsi", 3.75f);
		assertEquals(TestItem, ItemsDAO.create(TestItem));//will return null because read latest hasn't been implemnted
	}
}
