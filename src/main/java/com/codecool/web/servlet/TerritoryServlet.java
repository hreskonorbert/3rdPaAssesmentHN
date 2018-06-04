package com.codecool.web.servlet;

import com.codecool.web.dao.database.TerritoryDao;
import com.codecool.web.dto.MessageDto;
import com.codecool.web.model.Employee;
import com.codecool.web.model.Product;
import com.codecool.web.model.Territory;
import com.codecool.web.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/territoriesServlet")
public final class TerritoryServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            TerritoryDao tDao = new TerritoryDao(connection);
            List<Territory> territories = tDao.listAllTerritories();

            resp.setContentType("application/json");

            sendMessage(resp, HttpServletResponse.SC_OK, territories);




        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            TerritoryDao tDao = new TerritoryDao(connection);
            if(req.getParameter("territoryName")!=null && req.getParameter("territoryName")!= ""){
                int employeeId = ((Employee)req.getSession().getAttribute("employee")).getId();
                String territoryId = req.getParameter("territoryName");
                tDao.addTerritory(employeeId,territoryId);
                sendMessage(resp, HttpServletResponse.SC_OK, new MessageDto("Insertion executed"));

            }else{
                int employeeId = ((Employee)req.getSession().getAttribute("employee")).getId();
                List<Territory> territories = tDao.listAllTerritoriesById(employeeId);

                resp.setContentType("application/json");

                sendMessage(resp, HttpServletResponse.SC_OK, territories);

            }





        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
