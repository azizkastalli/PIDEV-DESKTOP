/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Vote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        String requete = "INSERT INTO rating (id_produit,vote) "
                    + "VALUES(?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setString(1,obj.getId_produit());
            pst.setDouble(2,(Double) obj.getVote());
                       
            
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
            rs.next();
   
             
                            
                       
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
    
}
