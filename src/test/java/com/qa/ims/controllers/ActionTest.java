package com.qa.ims.controllers;

import org.junit.Test;

import com.qa.ims.controller.Action;
import com.qa.ims.persistence.domain.Domain;

public class ActionTest {
	
	//I made changes to action these test below are checking that When i call this no exceptions 
	//are thrown since i added my own enum values to action
	@Test(expected = Test.None.class)
	public void TestActionOrder() {
		Action.printActions(Domain.ORDER);
	}
	@Test(expected = Test.None.class)
	public void TestActionOrderNonOrder() {
		Action.printActions(Domain.CUSTOMER);
	}
}
