package com.codecool.web.dao.database;

import com.codecool.web.model.Subordinate;
import com.codecool.web.model.Territory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class SubordinateDao extends AbstractDao{

    public SubordinateDao(Connection connection) {
        super(connection);
    }




    public List<Subordinate> findAllSubordinates(int employeeId) throws SQLException {
        List<Subordinate> result = new ArrayList<>();
        String sql = "SELECT first_name,last_name,title,country,postal_code,city,address,birth_date,hire_date, count(employee_territories.employee_id) as numOfTer \n" +
            "FROM employees\n" +
            "JOIN employee_territories ON employees.employee_id = employee_territories.employee_id\n" +
            "WHERE employees.reports_to = ?\n" +
            "GROUP BY first_name,last_name,title,country,postal_code,city,address,birth_date,hire_date\n";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(fetchSubordinate(resultSet));
                }
            }
        }
        return result;
    }

    private Subordinate fetchSubordinate(ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String title = rs.getString("title");
        String fullAddress = rs.getString("country")+","+rs.getString("postal_code")+","+rs.getString("city")+","+rs.getString("address");
        String birthDate = rs.getString("birth_date");
        String hirehDate = rs.getString("hire_date");
        int numOfTer = rs.getInt("numOfTer");
        return new Subordinate(firstName,lastName,title,fullAddress,birthDate,hirehDate,numOfTer);

    }

}
