package lk.ijse.PosBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {
    String id;
    String name;
    double price;
    int qty;
}

