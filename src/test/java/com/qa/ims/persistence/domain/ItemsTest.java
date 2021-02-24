package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemsTest {

	@Test
	public void CheckClassType() {//will check that when feed values it dosen't return null
		assertTrue(new Items("Pepsi", 3.75f) instanceof Items);
	}
}
