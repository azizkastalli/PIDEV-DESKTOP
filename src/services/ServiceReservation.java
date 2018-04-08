/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Participation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import utils.Dbconnection;



/**
 *
 * @author iheb bf
 */
public class ServiceReservation implements ICrud<Participation>{
    
      Connection cnx;

    public ServiceReservation() {
         cnx = Dbconnection.getInstance().getConnection();
        
    }
      

    @Override
    public void Create(Participation obj) throws SQLException {
          String requete = "INSERT INTO participation (id_evenement,id_client) "
                    + "VALUES(?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            
            pst.setInt(1,obj.getId_evenement());
            pst.setInt(2,obj.getId_client());
                           
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Participation obj) throws SQLException {
    }

    @Override
    public Participation Select(Participation obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participation> SelectAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Participation obj) throws SQLException {
        
           PreparedStatement pSmt = cnx.prepareStatement("delete from participation where id_client=? and id_evenement=? " );
         pSmt.setInt(1,obj.getId_client());
         pSmt.setInt(2,obj.getId_evenement());
         pSmt.executeUpdate();
    }
    
    public boolean VerifParticipation(int id_evenement,int id_client) {
          try {
             PreparedStatement myStmt = cnx.prepareStatement("SELECT * FROM participation where id_client=? and id_evenement=?");
            myStmt.setInt(1, id_client);
             myStmt.setInt(2, id_evenement);

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }      
        } catch (SQLException e) {

        }
return false;
    }
    
    
}
