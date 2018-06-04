package com.codecool.web.servlet;

import com.codecool.web.dao.database.SubordinateDao;
import com.codecool.web.dao.database.TerritoryDao;
import com.codecool.web.model.Employee;
import com.codecool.web.model.Subordinate;
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

@WebServlet("/subordinatesServlet")
public final class SubordinatesServlet extends AbstractServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            SubordinateDao sDao = new SubordinateDao(connection);
            int id = ((Employee)req.getSession().getAttribute("employee")).getId();
            List<Subordinate> subs = sDao.findAllSubordinates(id);
            resp.setContentType("application/json");

            sendMessage(resp, HttpServletResponse.SC_OK, subs);




        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
