/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.AnimalPerdu;
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
public class CrudAnimalperdu implements ICrud<AnimalPerdu>{
    Connection cnx ;
        
    public CrudAnimalperdu ()
    {
        cnx =Dbconnection.getInstance().getConnection();
    }

    @Override
    public void Create(AnimalPerdu obj) throws SQLException {
String query ="INSERT INTO animalperdu(id_animal,date_disparition,lieu_disparition,etat) VALUES (?,?,?,?)";
      PreparedStatement pSmt  = cnx.prepareStatement(query);
      pSmt.setInt(1,obj.getId_animal());
      pSmt.setDate(2, obj.getDate_disparition());
      pSmt.setString(3, obj.getLieu_disparition());
      pSmt.setBoolean(4, obj.isEtat());
      pSmt.executeUpdate();    
    }

    @Override
    public void Update(AnimalPerdu obj) throws SQLException {
String requete = "UPDATE animalperdu SET id_animal=?,lieu_disparition=?,date_disparition=?,etat=?  WHERE id=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,obj.getId_animal());
            pst.setString(2,obj.getLieu_disparition() );
            pst.setDate(3, obj.getDate_disparition());
            pst.setBoolean(4, obj.isEtat());
            pst.setInt(5, obj.getId());
           
            pst.executeUpdate();    }

    @Override
    public AnimalPerdu Select(AnimalPerdu obj) throws SQLException {
        String requete = "SELECT * FROM animalperdu where id=?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,obj.getId());
            ResultSet rs = pst.executeQuery();
            rs.next();
               
            obj.setId_animal(rs.getInt(1)); 
            obj.setLieu_dispairition(rs.getString(2)); 
            obj.setDate_disparition(rs.getDate(3)); 
            obj.setEtat(rs.getBoolean(4)); 
            
                
                return obj;

            
    }
    

    @Override
    public List SelectAll() throws SQLException {
List<AnimalPerdu> listAnimal = new ArrayList<>();
        
            String requete = "SELECT * FROM animalperdu ";

            PreparedStatement pst = cnx.prepareStatement(requete);
           

            ResultSet rs = pst.executeQuery();
            while (rs.next()) 
               
                {String etat;
                    if (rs.getBoolean(5)==true)
                    {
                      etat="trouvÃ©";
                           AnimalPerdu A =new AnimalPerdu(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getString(4),etat);   
            listAnimal.add(A);
                    }
               else {etat="perdue";
                       AnimalPerdu A =new AnimalPerdu(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getString(4),etat);   
            listAnimal.add(A);
                    
                    }
                    }
         
           

          
            return listAnimal;
    }

    @Override
    public void Delete(AnimalPerdu obj) throws SQLException {
 PreparedStatement pSmt = cnx.prepareStatement("delete from animalperdu where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();
    }
    
    public List<Integer> ExtractId() throws SQLException {
         List<Integer> liste = new ArrayList<>();
        String requete=" SELECT id FROM animal";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        ResultSet rs = pSmt.executeQuery();
        
           while(rs.next())
           {
         
           liste.add(rs.getInt("id"));
           }
           
     return liste;
    }

  
    
}
