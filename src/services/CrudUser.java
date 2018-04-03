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
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class CrudUser implements IUser<User>{

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
    public String GetUserRole(User usr)
    {
        try {
            PreparedStatement myStmt = cnx.prepareStatement("select roles from utilisateur where username = ?");
            myStmt.setString(1, usr.getUsername());
            ResultSet myRes= myStmt.executeQuery();
            while(myRes.next())
            {
                return myRes.getString("roles");
            }
                    } catch (SQLException ex) {
        }
        return "";
}
    @Override
    public void Create(User  obj) throws SQLException {
          
        String requete = "INSERT INTO Utilisateur (username,password,roles) "
                    + "VALUES(?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getUsername());
            pst.setString(2,obj.getPassword());
            pst.setString(3,obj.getRoles());                        
            
            pst.executeUpdate();
    }

    @Override
    public void Update(User  obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User Select(User obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User > SelectAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(User obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
