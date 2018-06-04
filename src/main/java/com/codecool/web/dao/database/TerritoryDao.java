package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class TerritoryDao extends AbstractDao{

    public TerritoryDao(Connection connection) {
        super(connection);
    }




    public List<Territory> listAllTerritories() throws SQLException {
        List<Territory> territories = new ArrayList<>();
        String sql = "SELECT territory_id,territory_description,territories.region_id,region.region_description FROM territories\n" +
            "JOIN region ON territories.region_id = region.region_id";

            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    territories.add(fetchTerritory(resultSet));
            }
        }
        return territories;
    }

    public List<Territory> listAllTerritoriesById(int employeeId) throws SQLException {
        List<Territory> territories = new ArrayList<>();
        String sql = "SELECT employee_territories.territory_id, territories.territory_description,territories.region_id, region.region_description FROM employee_territories\n" +
            "JOIN territories ON employee_territories.territory_id = territories.territory_id\n" +
            "JOIN region ON region.region_id = territories.region_id\n" +
            "WHERE employee_territories.employee_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    territories.add(fetchTerritory(resultSet));
                }
            }
        }
        return territories;
    }

    public void addTerritory(int employeeId, String territoryId) throws SQLException {

        String sql = "INSERT INTO employee_territories(employee_id,territory_id) VALUES (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.setString(2, territoryId);
            executeInsert(statement);


        }
    }

    private Territory fetchTerritory(ResultSet rs) throws SQLException {
        String id = rs.getString("territory_id");
        String territoryDescription = rs.getString("territory_description");
        int regionId= rs.getInt("region_id");

        String regionDescription = rs.getString("region_description");

        return new Territory(id,territoryDescription,regionId,regionDescription);

    }

}
