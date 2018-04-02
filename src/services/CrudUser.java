/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.User;
import utils.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class CrudUser implements IUser{

    Connection cnx;

    public CrudUser() {
        cnx = Dbconnection.getInstance().getConnection();
}
    @Override
    public boolean VerifyUser(User usr) {try {
            PreparedStatement myStmt = cnx.prepareStatement("SELECT * from utilisateur where username = ?");
            myStmt.setString(1, usr.getUsername());

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
return false;
    }

    @Override
    public boolean VerifyIfAdmin(User usr) {try {
             PreparedStatement myStmt = cnx.prepareStatement("SELECT * from utilisateur where username= ? and roles = 'a:1:{i:0;s:19:\"ROLE_ADMINISTRATEUR\";}'");
            myStmt.setString(1, usr.getUsername());

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }      
        } catch (Exception e) {

        }
return false;
    }

    public int GetUserId(User usr)
    {
        try {
            PreparedStatement myStmt = cnx.prepareStatement("select id from utilisateur where username = ?");
            myStmt.setString(1, usr.getUsername());
            ResultSet myRes= myStmt.executeQuery();
            while(myRes.next())
            {
                return myRes.getInt("id");
            }
                    } catch (SQLException ex) {
        }
        return 0;
}
    
}
