package lk.ijse.PosBackend.dao.custom.impl;

import lk.ijse.PosBackend.dao.custom.OrderDao;
import lk.ijse.PosBackend.entity.Item;
import lk.ijse.PosBackend.entity.Order;
import lk.ijse.PosBackend.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    static String SAVE_ORDER = "INSERT INTO orders (orderId, orderDate, custId, itemId, orderQty, total, amount, Discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";





    @Override
    public boolean save(Order entity, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(SAVE_ORDER);
            preparedStatement.setString(1, entity.getOrderId());
            preparedStatement.setString(2,entity.getOrderDate());
            preparedStatement.setString(3,entity.getCustId());
            preparedStatement.setString(4,entity.getItemId());
            preparedStatement.setInt(5,entity.getOrderQty());
            preparedStatement.setDouble(6,entity.getTotal());
            preparedStatement.setDouble(7,entity.getAmount());
            preparedStatement.setDouble(8,entity.getDiscount());


            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Order> getAll(Connection connection) {
        return null;
    }

    @Override
    public boolean update(Order entity, String Id, Connection connection) {
        return false;
    }

    @Override
    public boolean isExists(String id, Connection connection) {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }

    @Override
    public Order get(String id, Connection connection) {
        return null;
    }
}

