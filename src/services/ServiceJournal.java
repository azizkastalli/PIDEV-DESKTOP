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
        
        String requete = "INSERT INTO Journal (id_session,id_client,mise,date_mise) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getId_session());
            pst.setString(2,obj.getId_client());
            pst.setDouble(3,obj.getMise());
            pst.setTime(4,obj.getDate_mise());
            
            //à modifer apres avoir creer le module user et inserer directement par code l'id du user
            
            pst.executeUpdate();
            
            
            String requete2 = "UPDATE Session SET derniere_mise	= ? , id_gagnant = ? , etat='en cours' where id=? ";
          
              PreparedStatement pst2 = cnx.prepareStatement(requete2);
              pst2.setDouble(1,obj.getMise());
              pst2.setString(2,obj.getId_client());
              pst2.setString(3,obj.getId_session());
              
              pst2.executeUpdate();
             
    }
   
        public boolean ExistJournal(Session S) throws SQLException
    {
         
        String requete=" SELECT count(*) "
                     + "FROM Journal j "
                     + "JOIN utilisateur u on u.id=j.id_client "
                     + "WHERE j.id_session= ? ";

        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1,S.getId());
        ResultSet rs = pst.executeQuery();
        rs.next();
         
          if(rs.getInt(1)!=0)
            return true;
          
          return false;
    }
        
     
            public Journal Select(Session S) throws SQLException {
      Journal j = new Journal();
        String requete="SELECT j.id,j.date_mise,j.mise,u.username "
                     + "FROM Journal j "
                     + "JOIN utilisateur u on u.id=j.id_client "
                     + "WHERE j.id_session= ? ORDER BY j.id "
                     + "DESC LIMIT 1 ";
     
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,S.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            
            j.setNom_client(rs.getString(4));
            j.setMise(rs.getDouble(3));
            j.setDate_mise(rs.getTime(2));
                              
       return j;               
    }
        
}
