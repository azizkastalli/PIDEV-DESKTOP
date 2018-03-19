/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import config.Dbconnection;
import entites.Encheres;
import java.sql.Connection;
import java.sql.Date;
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
          String requete = "INSERT INTO Encheres (id_cible,id_propietaire,date_debut,seuil_mise,id_encheres) "
                    + "VALUES(?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getId_cible());
            pst.setString(2,obj.getId_proprietaire());
            pst.setDate(3, (Date) obj.getDate_debut());
            pst.setDouble(4,obj.getSeuil_mise());            
            pst.setDouble(5,obj.getId_encheres());            
            
            pst.executeUpdate();

            System.out.println("Enchere ajoutée");
    }

    @Override
    public void Update(Encheres obj) throws SQLException {
             
        String requete = "UPDATE Encheres SET date_debut=?,seuil_mise=? "
                    + "where id_encheres=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setDate(1, (Date) obj.getDate_debut());
            pst.setDouble(2,obj.getSeuil_mise());
            pst.setInt(3,obj.getId_encheres());
            
            pst.executeUpdate();
    }

    @Override
    public Encheres Select(Encheres obj) throws SQLException {
       
        String requete=" SELECT * FROM encheres WHERE id_encheres=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId_encheres());
        ResultSet rs = pSmt.executeQuery();
           
            obj.setId_encheres(rs.getInt(1)); 
            obj.setSeuil_mise(rs.getDouble(1)); 
            obj.setId_proprietaire(rs.getString(1)); 
            obj.setId_cible(rs.getString(1)); 
            obj.setDate_debut(rs.getDate(1)); 
                            
                       
       return obj;                 
    }

    @Override
    public List<Encheres> SelectAll() throws SQLException {
        List<Encheres> liste = new ArrayList<>();
        String requete=" SELECT * FROM encheres";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
           while(rs.next())
           {
            Encheres E =new Encheres(rs.getInt(1),rs.getDouble(1),rs.getString(1),rs.getString(1),rs.getDate(1));   
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
    
}
