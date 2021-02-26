package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemsController;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private ItemsDAO dao;

	@InjectMocks
	private ItemsController controller;
	@Ignore
	@Test
	public void CreateTest() {
		final String ItemName = "Pringles";
		final float ItemCost = 3.75f;
		final Items Item = new Items(ItemName, ItemCost);
		
		//setup what it should do 
		Mockito.when(utils.getString()).thenReturn(ItemName);
		Mockito.when(utils.getFloat()).thenReturn(ItemCost);
		Mockito.when(dao.create(Item)).thenReturn(Item);
		//pass what i want and what it should call to generate what i want
		assertEquals(Item, this.controller.create());
		
		//verify it did it how i want
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getFloat();
		Mockito.verify(dao, Mockito.times(1)).create(Item);
		
	}
	
	@Test
	public void ReadAllTest() {
		List<Items> Items = new ArrayList<>();
		Items.add(new Items("Pringles", 3.75f)); 

		Mockito.when(dao.readAll()).thenReturn(Items);

		assertEquals(Items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	@Ignore
	@Test
	public void UpdateTest() {
		Items NewItem = new Items(1l,"Quavers", 3.75f);

		Mockito.when(utils.getLong()).thenReturn(1L);//enter id 1
		Mockito.when(utils.getString()).thenReturn("Quavers");//pass quavers
		Mockito.when(utils.getFloat()).thenReturn(3.75f);//pass 3.75
		Mockito.when(dao.update(NewItem)).thenReturn(NewItem);

		assertEquals(NewItem, this.controller.update());//Check that they match

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getFloat();//check the calls
		Mockito.verify(this.dao, Mockito.times(1)).update(NewItem);
		
		
	}
	
	@Test
	public void DeleteTest() {
		final long DeleteID = 1L;

		Mockito.when(utils.getLong()).thenReturn(DeleteID);
		Mockito.when(dao.delete(DeleteID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(DeleteID);
	}
	
	
	@Test
	public void DeleteMoreThanOneTest() {
		final long DeleteID = 1L;

		Mockito.when(utils.getLong()).thenReturn(DeleteID);
		Mockito.when(dao.delete(DeleteID)).thenReturn(2);

		assertEquals(2L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(DeleteID);
	}
	
	
}
