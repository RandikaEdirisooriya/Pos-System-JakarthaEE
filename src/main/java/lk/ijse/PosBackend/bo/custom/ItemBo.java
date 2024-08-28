package lk.ijse.PosBackend.bo.custom;

import lk.ijse.PosBackend.bo.SuperBO;
import lk.ijse.PosBackend.dto.ItemDto;

import java.sql.Connection;
import java.util.List;

public interface ItemBo extends SuperBO {
    boolean saveItem(ItemDto itemDTO, Connection connection);
    List<ItemDto> getAllItem(Connection connection);
    boolean updateItem(ItemDto itemDTO, String Id, Connection connection);
    boolean isExistsItem(String id,Connection connection);
    boolean deleteItem(String id,Connection connection);
    ItemDto get(String id,Connection connection);
}
