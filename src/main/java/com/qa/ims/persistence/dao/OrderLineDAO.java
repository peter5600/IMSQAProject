package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderLines;
import com.qa.ims.utils.DBUtils;

public class OrderLineDAO implements Dao<OrderLines> {

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
			statement.setLong(3, Order.getQuantity());
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
	public OrderLines modelFromResultSet(ResultSet resultSet) throws SQLException {//values read correctley here not hex
		Long ID = resultSet.getLong("id");
		Long ItemID = resultSet.getLong("ItemID");
		Long Quantity = resultSet.getLong("Quantity");
		Long OrdersID = resultSet.getLong("OrdersID");
		return new OrderLines(ID, OrdersID, ItemID, Quantity);
	}

	public OrderLines ReadLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderlines ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<OrderLines> ReadAllOrdersBelongingToOrderID(long ID) {//This returns correct value as well
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM orderlines WHERE OrdersID = ?");) {
			statement.setLong(1, ID);
			List<OrderLines> OrderList = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					OrderList.add(modelFromResultSet(resultSet));
				}
			}
			return OrderList;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public int DeleteOrderLinesUsingOrderID(Long ID) { 
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orderlines WHERE OrdersID = ?");) {
			statement.setLong(1, ID); 
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;// no records deleted

	}
	
	public int DeleteOrderLinesUsingItemID(Long ID, Long OrdersID) { 
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orderlines WHERE ItemID = ? and OrdersID = ?");) {
			statement.setLong(1, ID); 
			statement.setLong(2, OrdersID); 
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;// no records deleted

	}
	
	public OrderLines AddItemsToOrderLines(Long OrderID, Long ItemID, Long Quantity) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orderlines(OrdersID, ItemID, Quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, OrderID); 
			statement.setLong(2, ItemID); 
			statement.setLong(3, Quantity);
			statement.executeUpdate();
			return ReadLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}
