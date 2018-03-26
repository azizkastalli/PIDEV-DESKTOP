/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.Dbconnection;
import entites.Wallet;
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
public class CrudWallet implements ICrud<Wallet>{

         Connection cnx;
     
    public CrudWallet() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    
    @Override
    public void Create(Wallet obj) throws SQLException {
    String requete = "INSERT INTO wallet (id_user,devise,somme) "
                    + "VALUES(?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getId_user());
            pst.setString(2,obj.getDevise());
            pst.setDouble(3,obj.getSomme());
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Wallet obj) throws SQLException {
        
        String requete = "UPDATE wallet SET devise=?,somme=? "
                    + "where id_user=?";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1,obj.getDevise());
            pst.setDouble(2,obj.getSomme());
            pst.setInt(3,obj.getId_user());
            pst.executeUpdate();

    }

    @Override
    public Wallet Select(Wallet obj) throws SQLException {
  String requete=" SELECT * FROM wallet WHERE id_user=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId_user());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setId(rs.getInt(1)); 
            obj.setId_user(rs.getInt(2)); 
            obj.setDevise(rs.getString(3)); 
            obj.setSomme(rs.getDouble(4)); 
                         
       return obj;                 
    }

    @Override
    public List<Wallet> SelectAll() throws SQLException {
        List<Wallet> liste = new ArrayList<>();
        String requete=" SELECT * FROM wallet ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Wallet W =new Wallet(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4));   
            liste.add(W);
           }
           
     return liste;
    }

    @Override
    public void Delete(Wallet obj) throws SQLException {
         PreparedStatement pSmt = cnx.prepareStatement("delete from wallet where id_user=? " );
         pSmt.setInt(1,obj.getId_user());
         pSmt.executeUpdate();
    }
    
}
