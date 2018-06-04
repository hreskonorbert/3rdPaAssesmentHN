package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Employee;
import com.codecool.web.model.Supplier;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public final class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            LoginService loginService = new SimpleLoginService(userDao);
            User user = null;
            if(req.getParameter("employeeId")!=null && req.getParameter("employeeId")!= ""){
                int employeeId = Integer.parseInt(req.getParameter("employeeId"));
                user=loginService.loginEmployee(employeeId);
                req.getSession().setAttribute("employee", (Employee) user);
            }else if(req.getParameter("supplierId")!=null && req.getParameter("supplierId")!= ""){
                int supplierId = Integer.parseInt(req.getParameter("supplierId"));
                user = loginService.loginSupplier(supplierId);
                req.getSession().setAttribute("supplier", (Supplier) user);

            }






            resp.setContentType("application/json");
            if(user!=null){
                sendMessage(resp, HttpServletResponse.SC_OK, user);
            }else{
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }


        } catch (ServiceException ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
