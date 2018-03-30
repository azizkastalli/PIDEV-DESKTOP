/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Rdv_Dresseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Dbconnection;

/**
 *
 * @author user
 */
public class CrudRdv_dresseur implements ICrud <Rdv_Dresseur>{
    
    Connection cnx ;
        
    public CrudRdv_dresseur ()
    {
        cnx =Dbconnection.getInstance().getConnection();
    }

    @Override
    public void Create(Rdv_Dresseur obj) throws SQLException {
        String query ="INSERT INTO `rdv_dresseur`(id_client,coordonnees,idAnimal,date_rdv,duree_seance,etat,id_dresseur) VALUES (?,?,?,?,?,?,?)";
      PreparedStatement pst  = cnx.prepareStatement(query);
      pst.setString(1,obj.getId_client());
     pst.setString(2, obj.getCoordonnees());
      pst.setInt(3, obj.getId_animal());
     pst.setDate(4, obj.getDate_rdv());
      pst.setTime(5, obj.getDuree_seance());
      pst.setBoolean(6, obj.isEtat());
      pst.setString(7, obj.getId_dresseur());
      pst.executeUpdate();   
    }

    @Override
    public void Update(Rdv_Dresseur obj) throws SQLException {
        String requete = "UPDATE rdv_dresseur SET id_client=?,coordonnees=?,idAnimal=?,date_rdv=?,duree_seance=?,etat=?,id_dresseur=? WHERE id=? ";
      PreparedStatement pst = cnx.prepareStatement(requete);
       pst.setString(1,obj.getId_client());
      pst.setString(2, obj.getCoordonnees());
      pst.setInt(3, obj.getId_animal());
      pst.setDate(4, obj.getDate_rdv());
      pst.setTime(5, obj.getDuree_seance());
      pst.setBoolean(6, obj.isEtat());
      pst.setString(7, obj.getId_dresseur());
      pst.setInt(8, obj.getId());
       pst.executeUpdate();


    }

    @Override
    public Rdv_Dresseur Select(Rdv_Dresseur obj) throws SQLException {
String requete = "SELECT * FROM rdv_dresseur where id=?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,obj.getId());

            ResultSet rs = pst.executeQuery();
            rs.next();
               
            obj.setId_client(rs.getString(1)); 
            obj.setCoordonnees(rs.getString(2)); 
            obj.setId_animal(rs.getInt(3)); 
            obj.setDate_rdv(rs.getDate(4)); 
            obj.setDuree_seance(rs.getTime(5)); 
            obj.setEtat(rs.getBoolean(6)); 
            obj.setId_dresseur(rs.getString(7));

                
                
                
              

          

        return obj;      }

    @Override
    public List<Rdv_Dresseur> SelectAll() throws SQLException {
List<Rdv_Dresseur> listRdv = new ArrayList<>();
String requete = "SELECT * FROM rdv_dresseur";

            PreparedStatement pst = cnx.prepareStatement(requete);
           

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
                Rdv_Dresseur R=new Rdv_Dresseur(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getTime(6), rs.getBoolean(7), rs.getString(8));
                listRdv.add(R);

            }
        

        return listRdv;     }

    @Override
    public void Delete(Rdv_Dresseur obj) throws SQLException {
PreparedStatement pSmt = cnx.prepareStatement("delete from rdv_dresseur where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();    }
    
}
