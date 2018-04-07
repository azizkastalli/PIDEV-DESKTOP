/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.AnimalSdf;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import utils.Dbconnection;

/**
 *
 * @author user
 */
public class CrudAnimalsdf implements ICrud<AnimalSdf>{

    Connection cnx ;
        
    public CrudAnimalsdf ()
    {
        cnx =Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(AnimalSdf obj) throws SQLException {
String query ="INSERT INTO animalsdf(id_categorie,sexe,nom_image,date_trouvaille,lieu_trouvaille,id_client) VALUES (?,?,?,?,?,?)";
      PreparedStatement pst  = cnx.prepareStatement(query);
      pst.setInt(1,obj.getId_categorie());
     pst.setString(2, obj.getSexe());
      pst.setString(3, obj.getNom_image());
     pst.setDate(4, obj.getDate_trouvaille());
      pst.setString(5, obj.getLieu_trouvaille());
      pst.setInt(6, obj.getId_client());
      
        
      pst.executeUpdate();    }

    @Override
    public void Update(AnimalSdf obj) throws SQLException {
String requete = "UPDATE animalsdf SET id_categorie=?,sexe=?, nom_image=?,date_trouvaille=?,lieu_trouvaille=?,id_client=? WHERE id=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
      pst.setInt(1,obj.getId_categorie());
      pst.setString(2, obj.getSexe());
      pst.setString(3, obj.getNom_image());
      pst.setDate(4, obj.getDate_trouvaille());
      pst.setString(5, obj.getLieu_trouvaille());
      pst.setInt(6, obj.getId_client());
      pst.setInt(7, obj.getId());
           
            pst.executeUpdate();    }

    @Override
    public AnimalSdf Select(AnimalSdf obj) throws SQLException {
 
            String requete = "SELECT * FROM animalsdf where id=?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,obj.getId());

            ResultSet rs = pst.executeQuery();
            rs.next();
               
            obj.setId_categorie(rs.getInt(1)); 
            obj.setSexe(rs.getString(2)); 
            obj.setNom_image(rs.getString(3)); 
            obj.setLieu_trouvaille(rs.getString(4)); 
            obj.setDate_trouvaille(rs.getDate(5)); 
            obj.setId_client(rs.getInt(6)); 

                
                
                
              

          

        return obj;    }

    @Override
    public List<AnimalSdf> SelectAll() throws SQLException {
         List<AnimalSdf> listAnimals = new ArrayList<>();
String requete = "SELECT * FROM animalsdf";

            PreparedStatement pst = cnx.prepareStatement(requete);
           

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
                 AnimalSdf A=new AnimalSdf(rs.getInt(1), rs.getString(4), rs.getDate(6), rs.getString(7), rs.getInt(3), rs.getString(5), rs.getInt(2));
                 listAnimals.add(A);
                
            }
        

        return listAnimals;    }

    @Override
    public void Delete(AnimalSdf obj) throws SQLException {
PreparedStatement pSmt = cnx.prepareStatement("delete from animalsdf where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();
    }
    
     public List<String> ExtractNom() throws SQLException {
         List<String> liste = new ArrayList<>();
        String requete=" SELECT nom FROM Categorie ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
         
           liste.add(rs.getString("nom"));
           }
           
     return liste;
    }
     public Integer ExtractId(String nom) throws SQLException {
        
        String requete=" SELECT id FROM Categorie where nom=? ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
       
        pSmt.setString(1,nom);
        ResultSet rs = pSmt.executeQuery();
rs.next();
       int id= rs.getInt("id");
        
          
     return id;
    }
    
}
