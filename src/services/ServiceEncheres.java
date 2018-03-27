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
import java.util.List;
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
   
    public ArrayList<Produit> Select9() throws SQLException
    {
      ArrayList<Produit> liste = new ArrayList<Produit>();
        String requete=" SELECT * FROM Produit LIMIT 9  ";

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
    
    public ArrayList<Produit> Select6(int nbr) throws SQLException
    {
      ArrayList<Produit> liste = new ArrayList<Produit>();
        String requete=" SELECT * FROM Produit LIMIT 6 OFFSET "+nbr;

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
    
}
