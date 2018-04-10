/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.Dbconnection;
import entites.Encheres;
import entites.Produit;
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
public class CrudEncheres implements ICrud<Encheres> {
 
     Connection cnx;
     
    public CrudEncheres() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(Encheres obj) throws SQLException {
        
        String requete = "INSERT INTO Encheres (id_cible,id_proprietaire,date_debut,seuil_mise) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getId_cible());
            
            //à modifer apres avoir creer le module user et inserer directement par code l'id du user
            pst.setInt(2,1);
            
            pst.setTimestamp(3,obj.getDate_debut());
            pst.setDouble(4,obj.getSeuil_mise());                        
            
            pst.executeUpdate();
            
            
            String requete2 = "UPDATE Produit SET etat=encheres where id=?";
            PreparedStatement pst2 = cnx.prepareStatement(requete2);
            pst2.setInt(1,obj.getId_cible());
            pst2.executeUpdate();
             
    }

    @Override
    public void Update(Encheres obj) throws SQLException {
             
         
        if(obj.getSeuil_mise()==0)
        {
        String requete = "UPDATE Encheres SET date_debut=?"
                    + "where id_encheres=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setTimestamp(1,obj.getDate_debut());
            pst.setInt(2,obj.getId_encheres());
            pst.executeUpdate();
        }        
        else
        {
        String requete = "UPDATE Encheres SET seuil_mise=? "
                    + "where id_encheres=?";
        
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1,obj.getSeuil_mise());        
            pst.setInt(2,obj.getId_encheres());
            pst.executeUpdate();
        }
        
    }

    @Override
    public Encheres Select(Encheres obj) throws SQLException {
        
        String item="";
          
        if(obj.getId_encheres()==0)
           item="e.id_cible";              
        else
           item="e.id_encheres";              
               
        String requete=" SELECT p.label,e.id_encheres,e.seuil_mise,e.date_debut,p.poid,p.nom_image,"
                + "u.username,c.nom,p.description,p.caracteristiques,s.etat "
                + "From produit p "
                + "JOIN encheres e on p.id=e.id_cible "
                + "JOIN categorie c on p.id_categorie=c.id "
                + "JOIN session s on e.id_encheres=s.id  "
                + "JOIN utilisateur u on p.id_propietaire=u.id "
                + "WHERE "+item+"=? "
                + "and s.etat<> 'fini' ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        
          if(obj.getId_encheres()==0)
                  pSmt.setInt(1,obj.getId_cible());              
        else
                  pSmt.setInt(1,obj.getId_encheres());
          
          
          ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setEtat(rs.getString(11)); 
            obj.setCaracteristiques(rs.getString(10)); 
            obj.setDescription(rs.getString(9)); 
            obj.setCategorie(rs.getString(8)); 
            obj.setNom_proprietaire(rs.getString(7)); 
            obj.setNom_image(rs.getString(6)); 
            obj.setPoid(rs.getDouble(5)); 
            obj.setLabel(rs.getString(1)); 
            obj.setId_encheres(rs.getInt(2)); 
            obj.setSeuil_mise(rs.getDouble(3)); 
            obj.setDate_debut(rs.getTimestamp(4)); 
                            
                       
       return obj;                 
    }

    @Override
    public List<Encheres> SelectAll() throws SQLException {
        List<Encheres> liste = new ArrayList<Encheres>();
        
         String requete="SELECT p.label,e.id_encheres,e.seuil_mise,e.date_debut,p.poid,p.nom_image,u.username,c.nom,p.description,p.caracteristiques,s.etat "
                      + "From produit p "
                      + "JOIN encheres e on p.id=e.id_cible "
                      + "JOIN session s on e.id_encheres=s.id  "
                      + "JOIN categorie c on p.id_categorie=c.id "
                      + "JOIN utilisateur u on p.id_propietaire=u.id "
                      + "Where  s.etat<> 'fini' ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Encheres E =new Encheres(rs.getString(11),rs.getString(10),rs.getString(9),rs.getString(8),rs.getString(7),
            rs.getString(6),rs.getDouble(5),rs.getString(1),rs.getInt(2),rs.getDouble(3),rs.getTimestamp(4));            
            liste.add(E);
           }
       
     return liste;
    }

    @Override
    public void Delete(Encheres obj) throws SQLException {
 
        PreparedStatement pSmt = cnx.prepareStatement("delete from Encheres where id_encheres=? " );
         pSmt.setInt(1,obj.getId_encheres());
         pSmt.executeUpdate();
         
        PreparedStatement pSmt2 = cnx.prepareStatement("delete from Produit where id=? " );
         pSmt2.setInt(1,obj.getId_cible());
         pSmt2.executeUpdate();

         
    }
    
    public List<Produit> SelectAllProduit() throws SQLException {
        List<Produit> liste = new ArrayList<Produit>();
     //change it
        String requete=" SELECT * FROM produit where quantite=1";

        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Produit P =new  Produit(rs.getString(5),rs.getString(7),rs.getString(6),rs.getInt(1),
                            rs.getInt(2),rs.getInt(3),rs.getString(12),rs.getDouble(8),
                            rs.getDouble(11),rs.getDouble(10),rs.getInt(4),rs.getDouble(9),rs.getString(13));   
           
            liste.add(P);
           }

     return liste;
    }
    
     public List<Encheres> SelectQuartz() throws SQLException {
        List<Encheres> liste = new ArrayList<Encheres>();
        
         String requete="SELECT e.id_encheres,e.date_debut,p.label "
                      + "From produit p "
                      + "JOIN encheres e on p.id=e.id_cible "
                      + "JOIN session s on e.id_encheres=s.id  "
                      + "JOIN categorie c on p.id_categorie=c.id "
                      + "JOIN utilisateur u on p.id_propietaire=u.id "
                      + "Where  s.etat<> 'fini' ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Encheres E =new Encheres(rs.getInt(1),rs.getTimestamp(2),rs.getString(3));            
            liste.add(E);
           }
       
     return liste;
    }
    
}