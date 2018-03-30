/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author azizkastalli
 */
public class Dbconnection {
     
    private final String url = "jdbc:mysql://localhost:3306/zanimauxintegré";
    private final String user = "root";
    private final String password = "";
    private Connection connection;
    private static Dbconnection data;

    public Dbconnection() {
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Dbconnection getInstance() {
        if (data == null) {
            data = new Dbconnection();
        }
        return data;
    }
}
