package edu.masanz.db;

import java.sql.*;

public class DbCon {

    private static Connection connection = null;

    public static boolean connect(String bd, String usuario, String contrasena){
        boolean exito = false;
        if (connection != null) {
            return true;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection( "jdbc:mysql://localhost/" + bd, usuario, contrasena ) ;
            connection.setAutoCommit(true);
            exito = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            connection = null;
        }
        return exito;
    }

    public static boolean isConnected() {
        if (connection == null) { return false; }
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static boolean disconnect() {
        boolean exito = false;
        try {
            if(connection !=null && !connection.isClosed()){
                connection.close();
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection = null;
        }
        return exito;
    }

}
