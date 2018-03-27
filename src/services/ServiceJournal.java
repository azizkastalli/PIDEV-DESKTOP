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
        String requete=" SELECT * FROM Journal WHERE id_session= ? ";

        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1,S.getId());
        ResultSet rs = pst.executeQuery();
        
           while(rs.next())
           {
            Journal J =new  Journal(rs.getTime(5),rs.getString(3),rs.getDouble(4));   
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
   
}
