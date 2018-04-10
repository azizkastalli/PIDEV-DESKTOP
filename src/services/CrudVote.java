/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Vote;
import static java.lang.Math.round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Dbconnection;

/**
 *
 * @author HP
 */
public class CrudVote implements ICrud<Vote>{
     Connection cnx;
     
    public CrudVote() {
        cnx = Dbconnection.getInstance().getConnection();
    }

    @Override
    public void Create(Vote obj) throws SQLException {
        String requete = "INSERT INTO rating (id_produit,vote,id_user) "
                    + "VALUES(?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setString(1,obj.getId_produit());
            pst.setDouble(2,(Double) obj.getVote());
            pst.setInt(3, obj.getId_user());
                       
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Vote obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vote Select(Vote obj) throws SQLException {
         String requete=" SELECT * FROM rating WHERE id_produit=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setString(1,obj.getId_produit());
        ResultSet rs = pSmt.executeQuery();
        float vote = rs.getFloat("vote");
                Vote v =new Vote(vote);
                
       return obj;  
        
    }

    @Override
    public List<Vote> SelectAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Vote obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Create(int id, Number t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   public Float Somme(String id_produit)  {
        List<Float> liste = new ArrayList<>();
        float s=0;
        DecimalFormat df = new DecimalFormat();
        
        try {
            
            String requete=" SELECT * FROM rating where id_produit=?";
            
            PreparedStatement pSmt = cnx.prepareStatement(requete);
            pSmt.setString(1, id_produit);
            ResultSet rs = pSmt.executeQuery();
            
            while(rs.next())
            {
                float vote = rs.getFloat("vote");
              
                Vote v = new Vote(vote);
                
                
                liste.add((Float) v.getVote());
            } 
                for (int i = 0; i < liste.size(); i++) {
                 s=liste.get(i)+s;   
                }
               s=((float) ((int) ((s/liste.size())*100))) / 100;
              
            
                                 
        } catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return s;
    }
      public boolean VerifyIfUser(int usr) {try {
             PreparedStatement myStmt = cnx.prepareStatement("SELECT * from rating where id_user=?");
            myStmt.setInt(1, usr);

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }      
        } catch (SQLException e) {

        }
return false;
    }
      public boolean VerifyIfprod(String usr) {
          try {
             PreparedStatement myStmt = cnx.prepareStatement("SELECT * from rating where id_produit=?");
            myStmt.setString(1, usr);

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }      
        } catch (SQLException e) {

        }
return false;
    }
}
