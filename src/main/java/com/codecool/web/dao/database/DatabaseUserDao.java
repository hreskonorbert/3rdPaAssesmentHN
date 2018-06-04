package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Employee;
import com.codecool.web.model.Supplier;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }



    @Override
    public User findEmployeeById(int employeeId) throws SQLException {

        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchEmployee(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public User findSupplierById(int supplierId) throws SQLException {

        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, supplierId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchSupplier(resultSet);
                }
            }
        }
        return null;
    }

    private User fetchEmployee(ResultSet rs) throws SQLException {
        int id=rs.getInt("employee_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String title = rs.getString("title");
        return new Employee(id,firstName,lastName,title);


    }

    private User fetchSupplier(ResultSet rs) throws SQLException {
        int id=rs.getInt("supplier_id");
        String companyName = rs.getString("company_name");
        String contactName = rs.getString("contact_name");
        String contactTitle = rs.getString("contact_title");
        return new Supplier(id,contactName,companyName,contactTitle);


    }
}
