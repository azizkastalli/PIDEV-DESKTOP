/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Dbconnection;
/**
 *
 * @author USER
 */
public class CrudCommande implements ICrud<Commande> {
    Connection cnx;
     
    public CrudCommande() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(Commande obj) throws SQLException {
          String requete = "INSERT INTO Commande (etat,id_client,prix_tot) "
                    + "VALUES(?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            
            pst.setInt(1,obj.getEtat());
            pst.setInt(2,obj.getId_client());
            pst.setDouble(3,obj.getPrix_tot());                        
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Commande obj) throws SQLException {
             
        String requete = "UPDATE Commande SET etat=?,prix_tot=? "
                    + "where id=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getEtat());
            pst.setDouble(2,obj.getPrix_tot());
            pst.setInt(3,obj.getId());
            
            pst.executeUpdate();
    }

    @Override
    public Commande Select(Commande obj) throws SQLException {
       
        String requete=" SELECT * FROM commande WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setId(rs.getInt(1)); 
            obj.setEtat(rs.getInt(2));
            obj.setId_client(rs.getInt(3));
            obj.setPrix_tot(rs.getDouble(4));  
                            
                       
       return obj;                 
    }

    @Override
    public List<Commande> SelectAll() {
        List<Commande> liste = new ArrayList<>();
        try {
        String requete=" SELECT * FROM commande ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Commande C =new Commande(rs.getInt(2),rs.getInt(1),rs.getInt(3),rs.getDouble(4));   
            liste.add(C);
           }
           
     return liste;}
        catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    @Override
    public void Delete(Commande obj) throws SQLException {
 
        PreparedStatement pSmt = cnx.prepareStatement("delete from Commande where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();

    }
    
}
