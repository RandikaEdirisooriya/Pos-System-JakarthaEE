package lk.ijse.PosBackend.dao.custom.impl;

import lk.ijse.PosBackend.dao.custom.CustomerDao;
import lk.ijse.PosBackend.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    static String SAVE_CUSTOMER = "INSERT INTO customer (id,name,address,contact,email) VALUES (?,?,?,?,?)";
    static String GET_CUSTOMER = "SELECT * FROM customer WHERE id=?";
    static String GET_ALL_CUSTOMER = "SELECT * FROM customer";
    static String UPDATE_CUSTOMER = "UPDATE customer SET name=?,address=?,contact=?,email=? WHERE id=?";
    static String DELETE_CUSTOMER= "DELETE FROM customer WHERE id=?";



    @Override
    public boolean save(Customer entity, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(SAVE_CUSTOMER);
            preparedStatement.setString(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getAddress());
            preparedStatement.setString(4, entity.getContact());
            preparedStatement.setString(5, entity.getEmail());


            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Customer> getAll(Connection connection) {
        List<Customer> customerList = new ArrayList<>();
        try {
            var ps = connection.prepareStatement(GET_ALL_CUSTOMER);
            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));


                customerList.add(customer);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public boolean update(Customer entity, String id, Connection connection) {
        // SQL query for updating an existing customer in the database
        String UPDATE_CUSTOMER = "UPDATE customer SET name = ?, address = ?, contact = ?, email = ? WHERE id = ?";

        PreparedStatement preparedStatement = null;
        try {
            // Prepare the statement with the SQL query
            preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);

            // Set the parameters based on the entity's fields
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAddress());
            preparedStatement.setString(3, entity.getContact());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, id);  // Use the provided id for the WHERE clause

            // Execute the update and return true if at least one row is updated
            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException e) {
            // Log the exception and rethrow it as a RuntimeException or handle it as needed
            e.printStackTrace();
            throw new RuntimeException("Error updating customer with id: " + id, e);
        } finally {
            // Ensure the PreparedStatement is closed to free resources
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean isExists(String id, Connection connection) {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement.setString(1,id);

            if (preparedStatement.executeUpdate() !=0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer get(String id, Connection connection) {
        var customer = new Customer();
        try {
            var ps = connection.prepareStatement(GET_CUSTOMER);
            ps.setString(1, id);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

}
