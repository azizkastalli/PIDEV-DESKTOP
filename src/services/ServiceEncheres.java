/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Encheres;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.Dbconnection;

/**
 *
 * @author azizkastalli
 */
public class ServiceEncheres {
      Connection cnx;
     
    public ServiceEncheres() {
        cnx = Dbconnection.getInstance().getConnection();
    }
   
    public ArrayList<Encheres> Select9() throws SQLException
    {
      ArrayList<Encheres> liste = new ArrayList<Encheres>();

       String requete="SELECT p.label,e.id_encheres,e.seuil_mise,e.date_debut,p.poid,p.nom_image,u.username,c.nom,p.description,p.caracteristiques "
                      + "From produit p "
                      + "JOIN encheres e on p.id=e.id_cible "
                      + "JOIN categorie c on p.id_categorie=c.id "
                      + "JOIN utilisateur u on p.id_propietaire=u.id"
                      + " LIMIT 9 ";
       
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Encheres E =new Encheres(rs.getString(10),rs.getString(9),rs.getString(8),rs.getString(7),
            rs.getString(6),rs.getDouble(5),rs.getString(1),rs.getInt(2),rs.getDouble(3),rs.getTimestamp(4));            

            liste.add(E);
           }

     return liste;
    }
    public ArrayList<Encheres> Select6(int nbr) throws SQLException
    {
      ArrayList<Encheres> liste = new ArrayList<Encheres>();

       String requete="SELECT p.label,e.id_encheres,e.seuil_mise,e.date_debut,p.poid,p.nom_image,u.username,c.nom,p.description,p.caracteristiques "
                      + "From produit p "
                      + "JOIN encheres e on p.id=e.id_cible "
                      + "JOIN categorie c on p.id_categorie=c.id "
                      + "JOIN utilisateur u on p.id_propietaire=u.id"
                      + " LIMIT 9 OFFSET "+nbr;
      
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Encheres E =new Encheres(rs.getString(10),rs.getString(9),rs.getString(8),rs.getString(7),
            rs.getString(6),rs.getDouble(5),rs.getString(1),rs.getInt(2),rs.getDouble(3),rs.getTimestamp(4));            

            liste.add(E);
           }

     return liste;
    }
    
}
