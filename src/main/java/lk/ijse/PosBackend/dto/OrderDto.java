package lk.ijse.PosBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    String orderId;
    String orderDate;
    String custId;
    String itemId;
    int orderQty;
    double total;
    double amount;
    double Discount;

}
