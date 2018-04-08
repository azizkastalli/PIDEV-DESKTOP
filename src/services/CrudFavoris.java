/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import static gui.controller.LoginController.loggduser;
import entites.Favoris;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Dbconnection;

/**
 *
 * @author HP
 */
public class CrudFavoris implements ICrud<Favoris>{
     Connection cnx;
     
    public CrudFavoris() {
        cnx = Dbconnection.getInstance().getConnection();
    }

    @Override
    public void Create(Favoris obj) throws SQLException {
       String requete = "INSERT INTO favoris (id_client,id_produit) "
                    + "VALUES(?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getId_client());
            pst.setString(2,obj.getId_produit());
                        
                       
            
            pst.executeUpdate();
    }

    
    @Override
    public Favoris Select(Favoris obj) throws SQLException {
        
        String requete=" SELECT * FROM favoris WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
             
            
            obj.setId_client(rs.getInt(1)); 
            obj.setId_produit(rs.getString(2)); 
             
                            
                       
       return obj;  
    }

    @Override
    public List<Favoris> SelectAll() throws SQLException {
        List<Favoris> liste = new ArrayList<>();
        String requete=" SELECT * FROM favoris ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Favoris F =new Favoris(rs.getInt(1),rs.getString(2));   
            liste.add(F);
           }
           
     return liste;
    }

    @Override
    public void Delete(Favoris obj) throws SQLException {
        PreparedStatement pSmt = cnx.prepareStatement("delete from favoris where id_client=? and id_produit=? " );
         pSmt.setInt(1,obj.getId_client());
         pSmt.setString(2,obj.getId_produit());
         pSmt.executeUpdate();
    }

    @Override
    public void Update(Favoris obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public boolean VerifyIfprod(int id,String nom) {
          try {
             PreparedStatement myStmt = cnx.prepareStatement("SELECT * FROM favoris where id_client=? and id_produit=?");
            myStmt.setInt(1, id);
             myStmt.setString(2, nom);

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }      
        } catch (SQLException e) {

        }
return false;
    }
     
 
}
