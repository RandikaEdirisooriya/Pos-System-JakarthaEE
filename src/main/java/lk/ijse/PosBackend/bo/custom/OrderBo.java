package lk.ijse.PosBackend.bo.custom;

import lk.ijse.PosBackend.bo.SuperBO;
import lk.ijse.PosBackend.dto.OrderDto;

import java.sql.Connection;

public interface OrderBo extends SuperBO {
    boolean saveOrder(OrderDto orderDTO, Connection connection);
}


