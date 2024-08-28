package lk.ijse.PosBackend.bo.custom.impl;

import lk.ijse.PosBackend.bo.custom.OrderBo;
import lk.ijse.PosBackend.dao.DAOFactory;
import lk.ijse.PosBackend.dao.custom.OrderDao;
import lk.ijse.PosBackend.dto.ItemDto;
import lk.ijse.PosBackend.dto.OrderDto;
import lk.ijse.PosBackend.entity.Item;
import lk.ijse.PosBackend.entity.Order;
import lk.ijse.PosBackend.entity.OrderDetail;

import java.sql.Connection;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao= (OrderDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ORDER);


    @Override
    public boolean saveOrder(OrderDto orderDTO, Connection connection) {
        return orderDao.save(new Order(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustId(),orderDTO.getItemId(),orderDTO.getOrderQty(),orderDTO.getTotal(),orderDTO.getAmount(),orderDTO.getDiscount()),connection);
    }
}



