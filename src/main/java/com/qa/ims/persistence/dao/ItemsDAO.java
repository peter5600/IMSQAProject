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

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAO implements Dao<Items> {
	
	public static final Logger LOGGER = LogManager.getLogger();//this was a pre-made logger 
	@Override
	public List<Items> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {//select all 
			List<Items> Items = new ArrayList<>();
			while (resultSet.next()) {
				Items.add(modelFromResultSet(resultSet));
			}
			return Items;//if everything ok return items list to be logged out
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();//else return empty list so that the error is displayed and then nothing else
	}

	@Override
	public Items read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Items create(Items t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO items(name, cost) VALUES (?, ?)");) {//user id doesn't exist yet so its just 1 
			statement.setString(1, t.getName());
			statement.setFloat(2, t.getCost());//when i add users change this back
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Items readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {//select all order by id limit to 1
			resultSet.next();//call this to move the cursor of the result set
			return modelFromResultSet(resultSet);//if everything ok return item to be logged out
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;//else return null 
	}

	@Override
	public Items update(Items t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE items SET name = ?, cost = ? WHERE id = ?");) {
			statement.setString(1, t.getName());
			statement.setFloat(2, t.getCost());
			statement.setLong(3, t.getID());
			statement.executeUpdate();
			return read(t.getID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE from items WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();//This give us an int which states how many rows were deleted
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;//if it failed it won't have deleted anything hence 0
	}

	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		//result set has a function called .getTYPE which lets me ask for the column in the row im after
		Long ID = resultSet.getLong("id");
		String ItemName = resultSet.getString("name");
		float ItemCost = resultSet.getFloat("cost");
		return new Items(ID, ItemName, ItemCost);
	}

}
