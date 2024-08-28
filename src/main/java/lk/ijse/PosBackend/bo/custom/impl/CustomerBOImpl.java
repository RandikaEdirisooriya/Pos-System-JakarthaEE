package lk.ijse.PosBackend.bo.custom.impl;

import lk.ijse.PosBackend.bo.custom.CustomerBO;
import lk.ijse.PosBackend.dao.DAOFactory;
import lk.ijse.PosBackend.dao.custom.CustomerDao;
import lk.ijse.PosBackend.dto.CustomerDTO;
import lk.ijse.PosBackend.entity.Customer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDao customerData= (CustomerDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.CUSTOMER);


    @Override
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerData.save(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContact(),customerDTO.getEmail()),connection);
    }

    @Override
    public List<CustomerDTO> getAllCustomer(Connection connection) {
        List<Customer> all = customerData.getAll(connection);
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        for (Customer customer : all) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setContact(customer.getContact());
            customerDTO.setEmail(customer.getEmail());

            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, String Id, Connection connection) {
        return customerData.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContact(),customerDTO.getEmail()),Id,connection);
    }

    @Override
    public boolean isExistsCustomer(String id, Connection connection) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) {
        return customerData.delete(id, connection);
    }

    @Override
    public CustomerDTO get(String id, Connection connection) {
        Customer customer = customerData.get(id, connection);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setContact(customer.getContact());
        customerDTO.setEmail(customer.getEmail());

        return customerDTO;
    }
}
