package lk.ijse.PosBackend.controller;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.PosBackend.bo.BOFactory;
import lk.ijse.PosBackend.bo.custom.OrderBo;
import lk.ijse.PosBackend.dto.OrderDto;
import lk.ijse.PosBackend.util.UtilProcess;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    Connection connection;

    private OrderBo orderBO= (OrderBo) BOFactory.getBoFactory().getBO(BOFactory.BOType.ORDER);


    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            OrderDto orderDTO = jsonb.fromJson(req.getReader(), OrderDto.class);
            String orderId= UtilProcess.generateId();
            orderDTO.setOrderId(orderId);
            boolean saveOrder = orderBO.saveOrder(orderDTO,connection);

            if (!saveOrder) {
                resp.getWriter().write("order not saved");
            }else {
                resp.getWriter().write("order saved successfully");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
