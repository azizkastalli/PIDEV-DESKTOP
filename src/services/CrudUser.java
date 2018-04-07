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
import java.util.ArrayList;
import java.util.Date;
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
    public String GetUsername(User usr)
    {
        try {
            PreparedStatement myStmt = cnx.prepareStatement("select nom from utilisateur where username = ?");
            myStmt.setString(1, usr.getUsername());
            ResultSet myRes= myStmt.executeQuery();
            while(myRes.next())
            {
                return myRes.getString("nom");
            }
                    } catch (SQLException ex) {
        }
        return "";
}
    public String GetUserprenom(User usr)
    {
        try {
            PreparedStatement myStmt = cnx.prepareStatement("select prenom from utilisateur where username = ?");
            myStmt.setString(1, usr.getUsername());
            ResultSet myRes= myStmt.executeQuery();
            while(myRes.next())
            {
                return myRes.getString("prenom");
            }
                    } catch (SQLException ex) {
        }
        return "";
}
    @Override
    public void Create(User  obj) throws SQLException {
          
        String requete = "INSERT INTO Utilisateur (username,password,roles,email,email_canonical,enabled,nom,prenom,username_canonical) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getUsername());
            pst.setString(2,obj.getPassword());
            pst.setString(3,obj.getRoles());  
            pst.setString(4,obj.getEmail());  
            pst.setString(5,obj.getEmail_canonical());  
            pst.setInt(6,obj.getEnabled());  
            pst.setString(7,obj.getNom());  
            pst.setString(8,obj.getPrenom());  
            pst.setString(9,obj.getUsername_canonical());  
            
            pst.executeUpdate();
    }

    @Override
    public void Update(User  obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void Updatepass(User  obj) throws SQLException {
        String requete = "UPDATE utilisateur SET password=? "
                    + "where username=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getPassword());
            pst.setString(2,obj.getUsername());
            
            pst.executeUpdate();
    }
    

    @Override
    public User Select(User obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User > SelectAll() {
        List<User> liste = new ArrayList<>();
        try {
            
            String requete=" SELECT * FROM utilisateur ";
            
            PreparedStatement pSmt = cnx.prepareStatement(requete);
            ResultSet rs = pSmt.executeQuery();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String confirmation_token = rs.getString("confirmation_token");
                String email = rs.getString("email");
                String email_canonical = rs.getString("email_canonical");
                Integer enabled = rs.getInt("enabled");
                Date last_login = rs.getDate("last_login");
                String nom = rs.getString("nom");
                String password = rs.getString("password");
                Date password_requested_at = rs.getDate("password_requested_at");
                String prenom = rs.getString("prenom");
                int prix_unitaire= rs.getInt("prix_unitaire");
                String roles = rs.getString("roles");
                String salt = rs.getString("salt");
                String username = rs.getString("username");
                String username_canonical = rs.getString("username_canonical");
                
                
                User u =new User(id,confirmation_token,email,email_canonical,enabled,last_login,nom,password,password_requested_at,prenom,prix_unitaire,roles,salt,username,username_canonical);
                liste.add(u);
            }
            
            return liste;
        } catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }


    @Override
    public void Delete(User obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String GetUsermail(User usr) {
        try {
            PreparedStatement myStmt = cnx.prepareStatement("select email from utilisateur where username = ?");
            myStmt.setString(1, usr.getUsername());
            ResultSet myRes= myStmt.executeQuery();
            while(myRes.next())
            {
                return myRes.getString("email");
            }
                    } catch (SQLException ex) {
        }
        return "";
    }
    
}
