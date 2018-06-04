package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.SQLException;

public interface UserDao {

    User findEmployeeById(int employeeId) throws SQLException;

    User findSupplierById(int supplierId) throws SQLException;
}
