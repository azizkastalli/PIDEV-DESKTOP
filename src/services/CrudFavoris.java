/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Favoris;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            pst.setInt(2,obj.getId_produit());
                        
                       
            
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
            obj.setId_produit(rs.getInt(2)); 
             
                            
                       
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
            Favoris F =new Favoris(rs.getInt(1),rs.getInt(2));   
            liste.add(F);
           }
           
     return liste;
    }

    @Override
    public void Delete(Favoris obj) throws SQLException {
        PreparedStatement pSmt = cnx.prepareStatement("delete from favoris where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();
    }

    @Override
    public void Update(Favoris obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
