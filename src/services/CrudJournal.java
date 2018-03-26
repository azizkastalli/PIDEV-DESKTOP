/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Journal;
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
public class CrudJournal implements ICrud<Journal> {
    
    Connection cnx;
     
    public CrudJournal() {
        cnx = Dbconnection.getInstance().getConnection();
    }
    
    @Override
    public void Create(Journal obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Journal obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Journal Select(Journal obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Journal> SelectAll() throws SQLException {
       List<Journal> liste = new ArrayList<>();
        String requete=" SELECT * FROM session ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
            Journal J =new Journal(rs.getTime(5),rs.getInt(1),rs.getString(3),rs.getString(2),rs.getDouble(4));   
            liste.add(J);
           }
           
     return liste;

    }

    @Override
    public void Delete(Journal obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
