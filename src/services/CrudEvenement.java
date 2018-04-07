/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import entites.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Dbconnection;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 *
 * @author azizkastalli
 */
public class CrudEvenement implements ICrud<Evenement> {
    
       Connection cnx;

    public CrudEvenement() {
        
                        cnx = Dbconnection.getInstance().getConnection();

    }
       
       

    @Override
    public void Create(Evenement obj) throws SQLException {
        
         String requete = "INSERT INTO evenement (nom,description,nbr_participants,id_categorie,nom_image,etat,date_debut,date_fin) VALUES(?,?,?,?,?,?,?,?)";

         
                       PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1,obj.getNom());
            pst.setString(2,obj.getDescription());
            pst.setInt(3,obj.getNbr_participants());
             pst.setInt(4,obj.getId_categorie());
           
            pst.setString(5,obj.getNom_image());
           pst.setBoolean(6,obj.getEtat());
            pst.setTimestamp(7,obj.getDate_debut());
           pst.setTimestamp(8,obj.getDate_fin());
            pst.executeUpdate();
    }

    @Override
    public void Update(Evenement obj) throws SQLException {
          String requete = "UPDATE Evenement SET etat=? "
                    + "where nom=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setBoolean(1,obj.getEtat());
           
            pst.setString(2,obj.getNom());
            
            pst.executeUpdate();
    }

       public void Update1(Evenement obj,String nom) throws SQLException {
          String requete = "UPDATE Evenement SET nom=?,description=?,nbr_participants=?,id_categorie=?,nom_image=?,etat=? "
                    + "where nom=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
           pst.setString(1,obj.getNom());
            pst.setString(2,obj.getDescription());
            pst.setInt(3,obj.getNbr_participants());
             pst.setInt(4,obj.getId_categorie());
           
            pst.setString(5,obj.getNom_image());
            pst.setBoolean(6,obj.getEtat());

        
           
              pst.setString(7,nom);
            pst.executeUpdate();
    }
       
       
    @Override
    public Evenement Select(Evenement obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement> SelectAll()  {
  List<Evenement> liste = new ArrayList<>();
         try {
          
             String requete=" SELECT * FROM evenement ";
             
             PreparedStatement pSmt = cnx.prepareStatement(requete);
             ResultSet rs = pSmt.executeQuery();
             
             while(rs.next())
             {
                 
                 Evenement ev=new Evenement(rs.getInt(1),rs.getString(4),rs.getString(5),rs.getInt(6), rs.getTimestamp(11), rs.getTimestamp(12), rs.getBoolean(10), rs.getString(8), rs.getInt(2));
                 liste.add(ev);
                 
             }
             
             
         } catch (SQLException ex) {
            
         }
         return liste;
    }
    
    
      public List<Evenement> AfficherTT()  {
  List<Evenement> liste = new ArrayList<>();
         try {
          
             String requete=" SELECT * FROM evenement where etat=1 ";
             
             PreparedStatement pSmt = cnx.prepareStatement(requete);
             ResultSet rs = pSmt.executeQuery();
             
             while(rs.next())
             {
                 Evenement ev=new Evenement(rs.getString(4),rs.getString(5),rs.getInt(6), rs.getTimestamp(11), rs.getTimestamp(12), rs.getBoolean(10), rs.getString(8), rs.getInt(2));
                 liste.add(ev);
                 
             }
             
             
         } catch (SQLException ex) {
            
         }
         return liste;
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

    @Override
    public void Delete(Evenement obj) throws SQLException {
          PreparedStatement pSmt = cnx.prepareStatement("delete from evenement where nom=? " );
         pSmt.setString(1,obj.getNom());
         pSmt.executeUpdate();
    }
    
}
