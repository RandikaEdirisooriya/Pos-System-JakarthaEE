package lk.ijse.PosBackend.bo.custom.impl;

import lk.ijse.PosBackend.bo.custom.ItemBo;
import lk.ijse.PosBackend.dao.DAOFactory;
import lk.ijse.PosBackend.dao.custom.ItemDao;
import lk.ijse.PosBackend.dto.ItemDto;
import lk.ijse.PosBackend.entity.Item;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDto itemDTO, Connection connection) {
        return itemDao.save(new Item(itemDTO.getId(),itemDTO.getName(),itemDTO.getPrice(),itemDTO.getQty()),connection);
    }

    @Override
    public List<ItemDto> getAllItem(Connection connection) {
        List<Item> all = itemDao.getAll(connection);
        List<ItemDto> itemDTOS=new ArrayList<>();

        for (Item item: all) {
            ItemDto itemDTO = new ItemDto();
            itemDTO.setId(item.getId());
            itemDTO.setName(item.getName());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQty(item.getQty());

            itemDTOS.add(itemDTO);
        }

        return itemDTOS;
    }

    @Override
    public boolean updateItem(ItemDto itemDTO, String Id, Connection connection) {
        return itemDao.update(new Item(itemDTO.getId(),itemDTO.getName(),itemDTO.getPrice(),itemDTO.getQty()), Id, connection);
    }

    @Override
    public boolean isExistsItem(String id, Connection connection) {
        return false;
    }

    @Override
    public boolean deleteItem(String id, Connection connection) {
        return itemDao.delete(id, connection);
    }

    @Override
    public ItemDto get(String id, Connection connection) {
        Item item = itemDao.get(id, connection);
        ItemDto itemDTO = new ItemDto();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setQty(item.getQty());

        return itemDTO;
    }
}


