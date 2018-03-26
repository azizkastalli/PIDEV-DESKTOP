/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Produit;
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
public class CrudProduit implements ICrud<Produit> {
    Connection cnx;
     
    public CrudProduit() {
        cnx = Dbconnection.getInstance().getConnection();
    }

    @Override
    public void Create(Produit obj) throws SQLException {
            String requete = "insert into produit (caracteristiques,description,poid,vote,prix_nouv,prix_ancien,id_propietaire,id_categorie,etat,nom_image,quantite,label)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pSmt = cnx.prepareStatement(requete);
            
      pSmt.setString(1,obj.getCaracteristiques());
      pSmt.setString(2,obj.getDescription());
      pSmt.setDouble(3,obj.getPoid());
      pSmt.setDouble(4,obj.getVote());
      pSmt.setDouble(5,obj.getPrix_nouv());
      pSmt.setDouble(6,obj.getPrix_ancien());
      pSmt.setInt(7,obj.getId_propietaire());
      pSmt.setInt(8,obj.getId_categorie());
      pSmt.setString(9,obj.getEtat());
      pSmt.setString(10,obj.getNom_image());
      pSmt.setInt(11,obj.getQuantite());
      pSmt.setString(12,obj.getLabel());
      
      pSmt.executeUpdate();
            
        System.out.println(obj.getPoid());
            
            
    }

    @Override
    public void Update(Produit obj) throws SQLException {
            
        String requete = "update produit set caracteristiques=? ,description=?,poid=?,vote=?,prix_nouv=?,prix_ancien=?,id_propietaire=?,id_categorie=?,etat=?,nom_image=?,quantite=?,label=?" 
                + "where id=? ";  
       PreparedStatement pSmt = cnx.prepareStatement(requete);
       
       pSmt.setString(1,obj.getCaracteristiques());
      pSmt.setString(2,obj.getDescription());
      pSmt.setDouble(3,obj.getPoid());
      pSmt.setDouble(4,obj.getVote());
      pSmt.setDouble(5,obj.getPrix_nouv());
      pSmt.setDouble(6,obj.getPrix_ancien());
      pSmt.setInt(7,obj.getId_propietaire());
      pSmt.setInt(8,obj.getId_categorie());
      pSmt.setString(9,obj.getEtat());
      pSmt.setString(10,obj.getNom_image());
      pSmt.setInt(11,obj.getQuantite());
      pSmt.setString(12,obj.getLabel());
      pSmt.setInt(13,obj.getId());
        
        pSmt.executeUpdate(); 

    }

    @Override
    public Produit Select(Produit obj) throws SQLException {
     String requete=" SELECT * FROM produit WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setCaracteristiques(rs.getString(1)); 
            obj.setDescription(rs.getString(2)); 
            obj.setPoid(rs.getDouble(3)); 
            obj.setVote(rs.getDouble(4)); 
            obj.setPrix_nouv(rs.getDouble(5));
            obj.setPrix_ancien(rs.getDouble(6)); 
            obj.setId_propietaire(rs.getInt(7)); 
            obj.setId_categorie(rs.getInt(8)); 
            obj.setEtat(rs.getString(9)); 
            obj.setNom_image(rs.getString(10)); 
            obj.setQuantite(rs.getInt(11)); 
            obj.setLabel(rs.getString(12)); 
             
                
                       
       return obj; 
    }

    @Override
    public List<Produit> SelectAll() throws SQLException {
        
        List<Produit> liste = new ArrayList<>();
        String requete=" SELECT * FROM produit ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Produit P =new Produit(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getDouble(7), rs.getDouble(8),rs.getDouble(9),rs.getInt(10), rs.getDouble(11),rs.getString(12));   
            liste.add(P);
           }
           
     return liste;
    }

    @Override
    public void Delete(Produit obj) throws SQLException {
            
        PreparedStatement pSmt = cnx.prepareStatement("delete from produit where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();

    }
    
}
