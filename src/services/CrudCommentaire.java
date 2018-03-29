/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Dbconnection;

/**
 *
 * @author HP
 */
public class CrudCommentaire implements ICrud<Commentaire>{
    
    Connection cnx;
     
    public CrudCommentaire() {
        cnx = Dbconnection.getInstance().getConnection();
    }

    @Override
    public void Create(Commentaire obj) throws SQLException {

            String requete = "INSERT INTO commentaire (date,id_cible,id_client,texte) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setDate(1,obj.getDate());
            pst.setInt(2,obj.getId_cible());
            pst.setInt(3,obj.getId_client());
            pst.setString(4,obj.getTexte());            
                       
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Commentaire obj) throws SQLException {
         String requete = "UPDATE commentaire SET texte=? "
                    + "where id=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getTexte());
            
            pst.executeUpdate();
    }

    @Override
    public Commentaire Select(Commentaire obj) throws SQLException {
        
        String requete=" SELECT * FROM commentaire WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setDate(rs.getDate(1)); 
            obj.setId_cible(rs.getInt(2)); 
            obj.setId_client(rs.getInt(3)); 
            obj.setTexte(rs.getString(4)); 
             
                            
                       
       return obj;  
    }

    @Override
    public List<Commentaire> SelectAll() throws SQLException {
        List<Commentaire> liste = new ArrayList<>();
        String requete=" SELECT * FROM encheres ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Commentaire C =new Commentaire(rs.getDate(1),rs.getInt(2),rs.getInt(3),rs.getString(4));   
            liste.add(C);
           }
           
     return liste;
    }

    @Override
    public void Delete(Commentaire obj) throws SQLException {
        PreparedStatement pSmt = cnx.prepareStatement("delete from commentaire where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();
    }
    
}