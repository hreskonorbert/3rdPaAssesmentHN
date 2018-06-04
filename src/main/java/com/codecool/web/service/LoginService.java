package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public interface LoginService {

    User loginEmployee(int employeeId) throws SQLException, ServiceException;

    User loginSupplier(int supplierId) throws SQLException, ServiceException;
}
