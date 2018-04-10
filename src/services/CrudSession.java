/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.Dbconnection;
import entites.Session;
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
public class CrudSession implements ICrud<Session>{

       Connection cnx;
     
    public CrudSession() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(Session obj) throws SQLException {
     String requete = "INSERT INTO session (id,etat,derniere_mise,id_gagnant) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,obj.getId());
            pst.setString(2,obj.getEtat());
            pst.setDouble(3,obj.getDerniere_mise());
            pst.setString(4,obj.getId_gagnant());            
            pst.executeUpdate();

    }

    @Override
    public void Update(Session obj) throws SQLException {
     String requete = "UPDATE session SET etat=?,derniere_mise=?,,id_gagnant=?"
                    + "where id=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getEtat());
            pst.setDouble(2,obj.getDerniere_mise());
            pst.setString(3,obj.getId_gagnant());        
            pst.setInt(4,obj.getId());
            
            pst.executeUpdate();

    }

    @Override
    public Session Select(Session obj) throws SQLException {
        String requete=" SELECT s.id,s.etat,s.derniere_mise,u.username,p.label,p.nom_image "
                       + "FROM session s "
                       + "Join produit p on p.id=(SELECT id_cible FROM Encheres WHERE id_encheres= s.id) "
                       + "Join utilisateur u on u.id=s.id_gagnant "
                       + " WHERE s.id=?";
     
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setId(rs.getInt(1)); 
            obj.setEtat(rs.getString(2)); 
            obj.setDerniere_mise(rs.getDouble(3)); 
            obj.setGagnant(rs.getString(4)); 
            obj.setNomProduit(rs.getString(5)); 
            obj.setImg(rs.getString(6)); 
                              
       return obj;               
    }

    @Override
    public List<Session> SelectAll() throws SQLException {
    List<Session> liste = new ArrayList<Session>();

            String requete=" SELECT s.id,s.etat,s.derniere_mise,u.username,p.label,p.nom_image "
                       + "FROM session s "
                       + "Join produit p on p.id=(SELECT id_cible FROM Encheres WHERE id_encheres= s.id) "
                       + "Join utilisateur u on u.id=s.id_gagnant ";
    
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Session S =new Session(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(6),rs.getString(5),rs.getString(4));   
            System.out.println(S);
            liste.add(S);
           }
           
     return liste;
    }

    @Override
    public void Delete(Session obj) throws SQLException {
         PreparedStatement pSmt = cnx.prepareStatement("delete from session where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();
    }
    
    
}
