package lk.ijse.PosBackend.controller;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.PosBackend.bo.BOFactory;
import lk.ijse.PosBackend.bo.custom.ItemBo;
import lk.ijse.PosBackend.dto.ItemDto;
import lk.ijse.PosBackend.util.UtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet("/item")
public class ItemController extends HttpServlet {
    Connection connection;

    private ItemBo itemBO= (ItemBo) BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM);

   /* static Logger logger = LoggerFactory.getLogger(CustomerController.class);*/


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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*logger.info("Inside item Get method");*/
        String id = req.getParameter("id");

        if (id == null) {
            GetAllItem(req, resp);
        }

        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            ItemDto itemDTO = itemBO.get(id, connection);
            PrintWriter writer = resp.getWriter();
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(itemDTO,writer);
        }catch (Exception e) {
           /* logger.error("Error: ",e.getMessage());*/
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* logger.info("Inside item Post method");*/
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDTO = jsonb.fromJson(req.getReader(), ItemDto.class);
            String CusId= UtilProcess.generateId();
            itemDTO.setId(CusId);
            boolean saveCustomer = itemBO.saveItem(itemDTO,connection);
            if (!saveCustomer) {
                resp.getWriter().write("Item not saved");
            }else {
                resp.getWriter().write("Item saved successfully");
            }
        }catch (Exception e) {
           /* logger.error("Error: ",e.getMessage());*/
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  logger.info("Inside item Put method");*/
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDTO = jsonb.fromJson(req.getReader(), ItemDto.class);
            boolean updateItem = itemBO.updateItem(itemDTO,String.valueOf(itemDTO.getId()), connection);
            if (updateItem) {
                resp.getWriter().write("Item update saved");
            }else {
                resp.getWriter().write("Item update not successfully");
            }
        }catch (Exception e) {
           /* logger.error("Error: ",e.getMessage());*/
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* logger.info("Inside item Delete method");*/
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDTO = jsonb.fromJson(req.getReader(), ItemDto.class);
            if (itemBO.deleteItem(String.valueOf(itemDTO.getId()), connection)) {
                resp.getWriter().write("Delete success");
            }else {
                resp.getWriter().write("Delete not success");
            }

        }catch (Exception e) {
            /*logger.error("Error: ",e.getMessage());*/
            e.printStackTrace();
        }
    }

    protected void GetAllItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* logger.info("Inside item GetAll method");*/

        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            List<ItemDto> allItem = itemBO.getAllItem(connection);
            PrintWriter writer = resp.getWriter();
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allItem,writer);
        }catch (Exception e) {
          /*  logger.error("Error: ",e.getMessage());*/
            e.printStackTrace();
        }
    }

}
