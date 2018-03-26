/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.Dbconnection;
import entites.Encheres;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azizkastalli
 */
public class CrudEncheres implements ICrud<Encheres> {
 
     Connection cnx;
     
    public CrudEncheres() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(Encheres obj) throws SQLException {
          String requete = "INSERT INTO Encheres (id_cible,id_proprietaire,date_debut,seuil_mise,id_encheres) "
                    + "VALUES(?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getId_cible());
            pst.setInt(2,obj.getId_proprietaire());
            pst.setTimestamp(3,obj.getDate_debut());
            pst.setDouble(4,obj.getSeuil_mise());            
            pst.setDouble(5,obj.getId_encheres());            
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Encheres obj) throws SQLException {
             
        String requete = "UPDATE Encheres SET date_debut=?,seuil_mise=?,id_proprietaire=?,id_cible=? "
                    + "where id_encheres=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setTimestamp(1,obj.getDate_debut());
            pst.setDouble(2,obj.getSeuil_mise());
            pst.setInt(3,obj.getId_proprietaire());
            pst.setInt(4,obj.getId_cible());
            pst.setInt(5,obj.getId_encheres());
            
            pst.executeUpdate();
    }

    @Override
    public Encheres Select(Encheres obj) throws SQLException {
       
        String requete=" SELECT * FROM encheres WHERE id_encheres=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId_encheres());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setId_encheres(rs.getInt(1)); 
            obj.setSeuil_mise(rs.getDouble(2)); 
            obj.setId_proprietaire(rs.getInt(3)); 
            obj.setId_cible(rs.getInt(4)); 
            obj.setDate_debut(rs.getTimestamp(5)); 
                            
                       
       return obj;                 
    }

    @Override
    public List<Encheres> SelectAll() throws SQLException {
        List<Encheres> liste = new ArrayList<Encheres>();
        String requete=" SELECT * FROM encheres ";

        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Encheres E =new Encheres(rs.getInt(1),rs.getDouble(5),rs.getInt(2),rs.getInt(3),rs.getTimestamp(4));   
            liste.add(E);
           }
       
     return liste;
    }

    @Override
    public void Delete(Encheres obj) throws SQLException {
 
        PreparedStatement pSmt = cnx.prepareStatement("delete from Encheres where id_encheres=? " );
         pSmt.setInt(1,obj.getId_encheres());
         pSmt.executeUpdate();

    }
    
    public List<Produit> SelectAllProduit() throws SQLException {
        List<Produit> liste = new ArrayList<Produit>();
        String requete=" SELECT * FROM produit ";

        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Produit P =new  Produit(rs.getString(5),rs.getString(7),rs.getString(6),rs.getInt(1),
                            rs.getInt(2),rs.getInt(3),rs.getString(12),rs.getDouble(8),
                            rs.getDouble(11),rs.getDouble(10),rs.getInt(4),rs.getDouble(9),rs.getString(13));   
           
            liste.add(P);
           }

     return liste;
    }
    
}