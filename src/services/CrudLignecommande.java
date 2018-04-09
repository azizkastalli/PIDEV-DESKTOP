/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entites.Lignecommande;
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
public class CrudLignecommande implements ICrud<Lignecommande> {
    Connection cnx;
     
    public CrudLignecommande() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(Lignecommande obj) throws SQLException {
          String requete = "INSERT INTO lignecommande (id_client,id_commande,id_produit,qte) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            
            pst.setInt(1,obj.getId_client());
            pst.setInt(2,obj.getId_commande());
            pst.setInt(3,obj.getId_produit());
            pst.setInt(4,obj.getQte());
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Lignecommande obj) throws SQLException {
             
        String requete = "UPDATE lignecommande SET qte=? "
                    + "where id=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getQte());
            pst.setInt(2,obj.getId());
            
            pst.executeUpdate();
    }

    @Override
    public Lignecommande Select(Lignecommande obj) throws SQLException {
       
        String requete=" SELECT * FROM lignecommande WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setId(rs.getInt(1)); 
            obj.setId_client(rs.getInt(2));
            obj.setId_commande(rs.getInt(3));
            obj.setId_produit(rs.getInt(4));  
            obj.setQte(rs.getInt(5));  
                            
                       
       return obj;                 
    }

    @Override
    public List<Lignecommande> SelectAll() throws SQLException {
        List<Lignecommande> liste = new ArrayList<>();
        String requete=" SELECT * FROM lignecommande ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Lignecommande C =new Lignecommande(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));   
            liste.add(C);
           }
           
     return liste;
    }
    public ArrayList<Lignecommande> SelectAll(int C) {
        ArrayList<Lignecommande> liste = new ArrayList<>();
        try {
        String requete=" SELECT * FROM lignecommande where id_commande=? ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,C);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Lignecommande lC =new Lignecommande(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));   
            liste.add(lC);
           }
           
     return liste;
        }
        catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    @Override
    public void Delete(Lignecommande obj) throws SQLException {
 
        PreparedStatement pSmt = cnx.prepareStatement("delete from lignecommande where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();

    }
    
}