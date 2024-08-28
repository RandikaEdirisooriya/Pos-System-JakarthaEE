package lk.ijse.PosBackend.bo.custom;

import lk.ijse.PosBackend.bo.SuperBO;
import lk.ijse.PosBackend.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    List<CustomerDTO> getAllCustomer(Connection connection);
    boolean updateCustomer(CustomerDTO customerDTO, String Id, Connection connection);
    boolean isExistsCustomer(String id,Connection connection);
    boolean deleteCustomer(String id,Connection connection);
    CustomerDTO get(String id,Connection connection);
}
