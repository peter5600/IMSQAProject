package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderTest {
	@Test
	public void CheckClassType() {//will check that when feed values it dosen't return null
		assertTrue(new Order(2l, 3l) instanceof Order);
	}
}
