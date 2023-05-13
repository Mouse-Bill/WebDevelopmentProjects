package org.bill.foodsystem.util.db;

import java.sql.*;

public class BaseDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://db4free.net:3306/bltestdb";
    private static final String USERNAME = "bill_test_admin";
    private static final String PASSWORD = "db4freeLHN2002";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Connect to DataBase
    public void open(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Create PreparedStatement
    private void createPreparedStatement(String sql, Object[] params) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; ++i) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Execute SQL Query
    public ResultSet executeQuery(String sql, Object[] params) {
        try {
            createPreparedStatement(sql, params);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // Execute SQL Update
    public int executeUpdate(String sql, Object[] params) {
        int result = -1;
        try {
            createPreparedStatement(sql, params);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Close Connection
    public void close(){
        if (this.resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                resultSet = null;
            }
        }
        if (this.preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                preparedStatement = null;
            }
        }
        if (this.connection != null){
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }
}
