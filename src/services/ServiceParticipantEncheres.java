/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Participantsencheres;
import entites.Session;
import entites.User;
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
public class ServiceParticipantEncheres implements ICrud<Participantsencheres> {
     Connection cnx;
      
     public ServiceParticipantEncheres()
     {
        cnx = Dbconnection.getInstance().getConnection();
     }
    
    @Override
    public void Create(Participantsencheres obj) throws SQLException {
        
        String requete = "INSERT INTO participantsencheres (id_session,id_user,debut_session)"
                + " VALUES(?,?,?) ";
        PreparedStatement psmt = cnx.prepareStatement(requete);
          psmt.setInt(1, obj.getId_session());
          psmt.setInt(2, obj.getId_user());
          psmt.setTimestamp(3, obj.getDebut_session());
        
          psmt.executeUpdate();
          
                }

    @Override
    public void Update(Participantsencheres obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Participantsencheres Select(Participantsencheres obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participantsencheres> SelectAll() throws SQLException {
    List <Participantsencheres> liste = new ArrayList<>();
        
         String requete="SELECT * From participantsencheres ";
        
         PreparedStatement pSmt = cnx.prepareStatement(requete);
         ResultSet rs = pSmt.executeQuery();
        
          while(rs.next())
           {
            Participantsencheres P =new Participantsencheres(rs.getInt(2),rs.getInt(3),rs.getTimestamp(4));            
            liste.add(P);
           }
       
     return liste;
    }

    @Override
    public void Delete(Participantsencheres obj) throws SQLException {
            
        PreparedStatement psmt = cnx.prepareStatement("Delete From participantsencheres where id_user=? ");
        psmt.setInt(1, obj.getId_user());
        psmt.executeUpdate();
    }
    
   
    public Boolean verifierExistance(User u) throws SQLException {
                 
        String requete=" SELECT COUNT(*) FROM participantsencheres WHERE id_user = ? ";    
        PreparedStatement psmt = cnx.prepareStatement(requete);
        psmt.setInt(1, u.getId());
          
          ResultSet rs = psmt.executeQuery();
            rs.next();
         if(rs.getInt(1)!=0)
             return true;
         else
             return false;
    }
    
    public Boolean verifierExistanceSession(User u,Session s) throws SQLException {
                 
        String requete=" SELECT COUNT(*) FROM participantsencheres WHERE id_user = ? and id_session = ? ";    
        PreparedStatement psmt = cnx.prepareStatement(requete);
        psmt.setInt(1, u.getId());
        psmt.setInt(2, s.getId());
          
          ResultSet rs = psmt.executeQuery();
            rs.next();
            
         if(rs.getInt(1)!=0)
             return true;
         else
             return false;
    }
}
