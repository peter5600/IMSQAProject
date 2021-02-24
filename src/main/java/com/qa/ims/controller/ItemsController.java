package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

public class ItemsController implements CrudController<Items>{
	
	private ItemsDAO ItemsDao;
	private Utils utils;//has a scanner and getString can be used 
	public static final Logger Logger = LogManager.getLogger();
	
	public ItemsController(ItemsDAO ItemsDAO, Utils Utils ) {
		this.ItemsDao = ItemsDAO;
		this.utils = Utils;
	}
	
	@Override
	public List<Items> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Items create() {
		Logger.info("Please enter a name for the Item.");
		String ItemName = utils.getString();
		Logger.info("Please enter a cost for the Item.");
		float cost = utils.getFloat();
		Items Item = ItemsDao.create(new Items(ItemName, cost));
		Logger.info("Item has been created.");
		return Item;
	}

	@Override
	public Items update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
