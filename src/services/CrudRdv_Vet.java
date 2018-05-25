/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import utils.Dbconnection;
import entites.Rdv_Veterinaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Yessine
 */
public class CrudRdv_Vet implements ICrud<Rdv_Veterinaire>{
    
    Connection cnx;

    public CrudRdv_Vet(Connection cnx) {
        this.cnx = cnx;
    }
    
    
    
     @Override
    public void Create(Rdv_Veterinaire obj) throws SQLException {
    String requete = "INSERT INTO `rdv_veterinaire` (`id`, `id_client`, `id_vet`, `date_rdv`, `prix`, `duree_seance`, `etat`) "
            + " VALUES (NULL, ?, ?, ?, ? , ?, ? ); ";

            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setInt(1,obj.getId_client());
            pst.setInt(2,obj.getId_vet());
            String dt = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss ").format(new Date());
            pst.setString(3,dt);
            pst.setDouble(4,obj.getPrix());
            pst.setInt(5,obj.getDuree_seance());
            pst.setInt(6,obj.isEtat());
            
            pst.executeUpdate();
    }

    @Override
    public void Update(Rdv_Veterinaire obj) throws SQLException {
        System.out.println("update rdv :: id :: "+obj.getId());
        String requete=" Update rdv_veterinaire set id_client=? , id_vet=? , date_rdv=? , prix=? , duree_seance=? , etat=? "
          + "where id=? ";
        
        PreparedStatement pst = cnx.prepareStatement(requete);
        
        pst.setInt( 1 ,obj.getId_client());
        pst.setInt( 2 ,obj.getId_client());
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(obj.getDate_rdv());
        pst.setString(3 ,s);
        pst.setDouble(4 ,obj.getPrix());
        pst.setInt(5 ,obj.getDuree_seance());
        pst.setInt(6 ,obj.isEtat());
        pst.setInt(7 ,obj.getId());
        
        pst.executeUpdate();
        

    }

    @Override
    public Rdv_Veterinaire Select(Rdv_Veterinaire obj) throws SQLException {
  String requete=" SELECT * FROM rdv_veterinaire "
          + "where id=? ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setId_client(rs.getInt(2)); 
            obj.setId_vet(rs.getInt(3)); 
            
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rs.getString(4));
            obj.setDate_rdv(date); 
        } catch (ParseException ex) {
            Logger.getLogger(CrudRdv_Vet.class.getName()).log(Level.SEVERE, null, ex);
        }

            obj.setPrix(rs.getDouble(6)); 
            obj.setDuree_seance(rs.getInt(5)); 
            obj.setEtat(rs.getInt(7)); 
                         
       return obj;                 
    }

    @Override
    public List<Rdv_Veterinaire> SelectAll() throws SQLException {
        List<Rdv_Veterinaire> liste = new ArrayList<>();
        String requete=" SELECT * FROM rdv_veterinaire ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
               
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rs.getString(4));
                Rdv_Veterinaire W =new Rdv_Veterinaire(rs.getInt(1),rs.getInt(2),rs.getInt(3),date ,rs.getInt(5) , rs.getDouble(6), rs.getInt(7));   
                liste.add(W);
            } catch (ParseException ex) {
                Logger.getLogger(CrudRdv_Vet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           }
           
     return liste;
    }

    @Override
    public void Delete(Rdv_Veterinaire obj) throws SQLException {
         PreparedStatement pSmt = cnx.prepareStatement("delete from rdv_veterinaire where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();
    }
    
    public List<Rdv_Veterinaire> SelectAllByClient(int id) throws SQLException {
        List<Rdv_Veterinaire> liste = new ArrayList<>();
        String requete=" SELECT * FROM rdv_veterinaire "
                + "where id_client = ? ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1, id);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
               
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rs.getString(4));
                Rdv_Veterinaire W =new Rdv_Veterinaire(rs.getInt(1),rs.getInt(2),rs.getInt(3),date ,rs.getInt(5) , rs.getDouble(6), rs.getInt(7));   
                liste.add(W);
            } catch (ParseException ex) {
                Logger.getLogger(CrudRdv_Vet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           }
           
     return liste;
    }
    
    public List<Rdv_Veterinaire> SelectAllByVet(int id) throws SQLException {
        List<Rdv_Veterinaire> liste = new ArrayList<>();
        String requete=" SELECT * FROM rdv_veterinaire "
                + "where id_vet = ? ";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1, id);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
               
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(rs.getString(4));
                Rdv_Veterinaire W =new Rdv_Veterinaire(rs.getInt(1),rs.getInt(2),rs.getInt(3),date ,rs.getInt(5) , rs.getDouble(6), rs.getInt(7));   
                liste.add(W);
            } catch (ParseException ex) {
                Logger.getLogger(CrudRdv_Vet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           }
           
     return liste;
    }
    
}
