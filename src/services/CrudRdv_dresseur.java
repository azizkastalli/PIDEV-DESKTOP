/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Rdv_Dresseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rdv_Dresseur Select(Rdv_Dresseur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rdv_Dresseur> SelectAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Rdv_Dresseur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
