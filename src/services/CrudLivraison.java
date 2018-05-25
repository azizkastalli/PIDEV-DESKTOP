/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Commande;
import entites.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Dbconnection;
/**
 *
 * @author USER
 */
public class CrudLivraison implements ICrud<Livraison> {
    Connection cnx;
     
    public CrudLivraison() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(Livraison obj) throws SQLException {
          String requete = "INSERT INTO livraison (etat,id_client,id_livreur,id_commande) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            
            pst.setInt(1,obj.getEtat());
            pst.setInt(2,obj.getId_client());
            pst.setInt(3,obj.getId_livreur());  
            pst.setInt(4,obj.getId_commande());  
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Livraison obj) throws SQLException {
             
        String requete = "UPDATE livraison SET etat=? "
                    + "where id=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getEtat());
            pst.setInt(2,obj.getId());
            
            pst.executeUpdate();
    }

    @Override
    public Livraison Select(Livraison obj) throws SQLException {
       
        String requete=" SELECT * FROM livraison WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setId(rs.getInt(1)); 
            obj.setId_commande(rs.getInt(2));
            obj.setId_livreur(rs.getInt(3));
            obj.setId_client(rs.getInt(4));  
            obj.setEtat(rs.getInt(5));  
                            
                       
       return obj;                 
    }

    @Override
    public List<Livraison> SelectAll() {
        List<Livraison> liste = new ArrayList<>();
        try {
        String requete=" SELECT * FROM livraison ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Livraison C =new Livraison(rs.getInt(5),rs.getInt(1),rs.getInt(4),rs.getInt(2),rs.getInt(3));   
            liste.add(C);
           }
           
     return liste;}
        catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    @Override
    public void Delete(Livraison obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
