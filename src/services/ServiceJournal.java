/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Journal;
import entites.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import utils.Dbconnection;

/**
 *
 * @author azizkastalli
 */
public class ServiceJournal {
    
    Connection cnx;
     
    public ServiceJournal() {
        cnx = Dbconnection.getInstance().getConnection();
    }
   
    public List<Journal> SelectJournal(Session S) throws SQLException
    {
         List<Journal> liste = new ArrayList<Journal>();
        String requete=" SELECT j.id,j.date_mise,j.mise,u.username "
                     + "FROM Journal j "
                     + "JOIN utilisateur u on u.id=j.id_client "
                     + "WHERE j.id_session= ? ";

        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1,S.getId());
        ResultSet rs = pst.executeQuery();
        
           while(rs.next())
           {
            Journal J =new Journal(rs.getInt(1),rs.getTime(2),rs.getDouble(3),rs.getString(4));   
            liste.add(J);
           }

     return liste;
    }
    
    public void DeleteJournal(Session S) throws SQLException
    {
        String requete=" Delete FROM Journal WHERE id_session= ? ";

        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1,S.getId());
        pst.executeUpdate();
    }
    
        public void Create(Journal obj) throws SQLException {
        
        String requete = "INSERT INTO Journal (id_cible,id_proprietaire,date_debut,seuil_mise) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
      //      pst.setInt(1,obj.getDate_mise());
            
            //à modifer apres avoir creer le module user et inserer directement par code l'id du user
            pst.setInt(2,1);
            
      //      pst.setTimestamp(3,obj.getDate_debut());
      //      pst.setDouble(4,obj.getSeuil_mise());                        
            
            pst.executeUpdate();
            
            
            String requete2 = "UPDATE Produit SET etat=encheres where id=?";
            PreparedStatement pst2 = cnx.prepareStatement(requete);
       //     pst2.setInt(1,obj.getId_cible());
            pst2.executeUpdate();
             
    }
   
}
