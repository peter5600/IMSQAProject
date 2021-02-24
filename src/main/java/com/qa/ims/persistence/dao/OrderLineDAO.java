package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderLines;
import com.qa.ims.utils.DBUtils;

public class OrderLineDAO implements Dao<OrderLines>{

	public static final Logger LOGGER = LogManager.getLogger();
	@Override
	public List<OrderLines> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderLines read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderLines create(OrderLines Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO OrderLines(OrdersID, ItemID, Quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, Order.getOrdersID());
			statement.setLong(2, Order.getItemID());
			statement.setLong(2, Order.getQuantity());
			statement.executeUpdate();
			return ReadLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderLines update(OrderLines t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderLines modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OrderLines ReadLatest() {
		return null;
	}

	

}
