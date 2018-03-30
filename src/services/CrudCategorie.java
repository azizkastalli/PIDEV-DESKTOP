/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Dbconnection;

/**
 *
 * @author azizkastalli
 */
public class CrudCategorie implements ICrud<Categorie> {
     Connection cnx;

    public CrudCategorie() {
        
                cnx = Dbconnection.getInstance().getConnection();

    }

    @Override
    public void Create(Categorie obj) throws SQLException {
         String requete = "INSERT INTO Categorie (nom,type) "
                    + "VALUES(?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getNom());
            pst.setString(2,obj.getType());
                     
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Categorie obj) throws SQLException {
         String requete = "UPDATE Categorie SET nom=?,type=?"+      "where id=?";
                  
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getNom());
            pst.setString(2,obj.getType());
            pst.setInt(3,obj.getId());
            
            pst.executeUpdate();
    }

 
    @Override
    public Categorie Select(Categorie obj) throws SQLException {
        
         String requete=" SELECT * FROM Categorie WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setNom(rs.getString(1)); 
            obj.setType(rs.getString(2)); 
            
                            
                       
       return obj;                 
    }

    @Override
    public List<Categorie> SelectAll() throws SQLException {
         List<Categorie> liste = new ArrayList<>();
        String requete=" SELECT * FROM Categorie ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
           Categorie Cat=new Categorie(rs.getInt(1),rs.getString(2),rs.getString(3));
           liste.add(Cat);
           }
           
     return liste;
    }

    @Override
    public void Delete(Categorie obj) throws SQLException {
        PreparedStatement pSmt = cnx.prepareStatement("delete from Categorie where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();

    }
        
    }

   
     


