package lk.ijse.controller;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.util.UtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    Connection connection;

    private CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER);

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);



    @Override
    public void init() throws ServletException {

        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/yourshop");
            this.connection = pool.getConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Inside customer post method");
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            String CusId= UtilProcess.generateId();
            customerDTO.setId(CusId);
            boolean saveCustomer = customerBO.saveCustomer(customerDTO, connection);
            if (!saveCustomer) {
                resp.getWriter().write("Customer not saved");
            }else {
                resp.getWriter().write("Customer saved successfully");
            }
        }catch (Exception e) {
            logger.error("Error: ",e.getMessage());
            e.printStackTrace();
        }
    }

   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Inside customer Get method");

        String id = req.getParameter("id");
        if (id == null) {
            GetAllCustomer(req, resp);
        }

        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            CustomerDTO customer = customerBO.get(id, connection);
            PrintWriter writer = resp.getWriter();
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(customer,writer);
        }catch (Exception e) {
            logger.error("Error: ",e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Inside customer Put method");
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            boolean updateCustomer = customerBO.updateCustomer(customerDTO, String.valueOf(customerDTO.getId()),connection);
            if (updateCustomer) {
                resp.getWriter().write("Customer update saved");
            }else {
                resp.getWriter().write("Customer update successfully");
            }
        }catch (Exception e) {
            logger.error("Error: ",e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Inside customer Delete method");
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            if (customerBO.deleteCustomer(String.valueOf(customerDTO.getId()), connection)) {
                resp.getWriter().write("Delete success");
            }else {
                resp.getWriter().write("Delete not success");
            }

        }catch (Exception e) {
            logger.error("Error: ",e.getMessage());
            e.printStackTrace();
        }
    }

    protected void GetAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Inside customer GetAll method");

        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            List<CustomerDTO> allCustomer = customerBO.getAllCustomer(connection);
            PrintWriter writer = resp.getWriter();
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allCustomer,writer);
        }catch (Exception e) {
            logger.error("Error: ",e.getMessage());
            e.printStackTrace();
        }
    }
*/
}
