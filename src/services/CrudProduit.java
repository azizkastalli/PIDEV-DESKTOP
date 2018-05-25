/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Pagination;
import utils.Dbconnection;

/**
 *
 * @author azizkastalli
 */
public class CrudProduit implements ICrud<Produit> {
    Connection cnx;
     
    public CrudProduit() {
        cnx = Dbconnection.getInstance().getConnection();
    }

    public CrudProduit(Pagination layout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Create(Produit obj) throws SQLException {
            String requete = "insert into produit (caracteristiques,description,poid,vote,prix_nouv,prix_ancien,id_propietaire,id_categorie,etat,nom_image,quantite,label)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pSmt = cnx.prepareStatement(requete);
            
      pSmt.setString(1,obj.getCaracteristiques());
      pSmt.setString(2,obj.getDescription());
      pSmt.setDouble(3,obj.getPoid());
      pSmt.setDouble(4,obj.getVote());
      pSmt.setDouble(5,obj.getPrix_nouv());
      pSmt.setDouble(6,obj.getPrix_ancien());
      pSmt.setInt(7,obj.getId_propietaire());
      pSmt.setString(8,obj.getId_categorie());
      pSmt.setString(9,obj.getEtat());
      pSmt.setString(10,obj.getNom_image());
      pSmt.setInt(11,obj.getQuantite());
      pSmt.setString(12,obj.getLabel());
      
      pSmt.executeUpdate();
            
       
            
            
    }

    @Override
    public void Update(Produit obj) throws SQLException {
            
        String requete = "update produit set etat=? "
                + "where label=? ";  
       PreparedStatement pSmt = cnx.prepareStatement(requete);
       
      pSmt.setString(1,obj.getEtat());
      pSmt.setString(2,obj.getLabel());
        
        pSmt.executeUpdate(); 

    }

    @Override
    public Produit Select(Produit obj) throws SQLException {
     String requete=" SELECT * FROM produit WHERE id=?";
        
        PreparedStatement pSmt = cnx.prepareStatement(requete);
        pSmt.setInt(1,obj.getId());
        ResultSet rs = pSmt.executeQuery();
            rs.next();
            obj.setCaracteristiques(rs.getString(1)); 
            obj.setDescription(rs.getString(2)); 
            obj.setPoid(rs.getDouble(3)); 
            obj.setVote(rs.getDouble(4)); 
            obj.setPrix_nouv(rs.getDouble(5));
            obj.setPrix_ancien(rs.getDouble(6)); 
            obj.setId_propietaire(rs.getInt(7)); 
            obj.setId_categorie(rs.getString(8)); 
            obj.setEtat(rs.getString(9)); 
            obj.setNom_image(rs.getString(10)); 
            obj.setQuantite(rs.getInt(11)); 
            obj.setLabel(rs.getString(12)); 
             
                
                       
       return obj; 
    }

    @Override
    public List<Produit> SelectAll()  {
        List<Produit> liste = new ArrayList<>();
        try {
            
            String requete=" SELECT * FROM produit ";
            
            PreparedStatement pSmt = cnx.prepareStatement(requete);
            ResultSet rs = pSmt.executeQuery();
            
            while(rs.next())
            {
                String caracteristiques = rs.getString("caracteristiques");
                String description = rs.getString("description");
                String etat = rs.getString("etat");
                String id_categorie = rs.getString("id_categorie");
                Integer id_propietaire = rs.getInt("id_propietaire");
                String nom_image = rs.getString("nom_image");
                Double poid = rs.getDouble("poid");
                Double prix_ancien = rs.getDouble("prix_ancien");
                Double prix_nouv = rs.getDouble("prix_nouv");
                Integer quantite = rs.getInt("quantite");
                Double vote= rs.getDouble("vote");
                String label = rs.getString("label");
                
                
                Produit P =new Produit(caracteristiques,description,etat,id_categorie,id_propietaire,nom_image,poid,prix_ancien,prix_nouv,quantite,vote,label);
                liste.add(P);
            }
            
            return liste;
        } catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    @Override
    public void Delete(Produit obj) throws SQLException {
            
        PreparedStatement pSmt = cnx.prepareStatement("delete from produit where id=? " );
         pSmt.setInt(1,obj.getId());
         pSmt.executeUpdate();

    }
 
        public List<Produit> filtreEtat()  {
        List<Produit> liste = new ArrayList<>();
        try {
            
            String requete=" SELECT * FROM produit where etat='confirmer' ";
            
            PreparedStatement pSmt = cnx.prepareStatement(requete);
            ResultSet rs = pSmt.executeQuery();
            
            while(rs.next())
            {
                String caracteristiques = rs.getString("caracteristiques");
                String description = rs.getString("description");
                String etat = rs.getString("etat");
                String id_categorie = rs.getString("id_categorie");
                Integer id_propietaire = rs.getInt("id_propietaire");
                String nom_image = rs.getString("nom_image");
                Double poid = rs.getDouble("poid");
                Double prix_ancien = rs.getDouble("prix_ancien");
                Double prix_nouv = rs.getDouble("prix_nouv");
                Integer quantite = rs.getInt("quantite");
                Double vote= rs.getDouble("vote");
                String label = rs.getString("label");
                Integer id = rs.getInt("id");
                
                
                
                Produit P =new Produit(caracteristiques,description,etat,id,id_categorie,id_propietaire,nom_image,poid,prix_ancien,prix_nouv,quantite,vote,label);
                liste.add(P);
            }
            
            return liste;
        } catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
            
   public List<Produit> prodfav(String prod) {
       List<Produit> liste = new ArrayList<>(); 
       try {
            
            PreparedStatement myStmt = cnx.prepareStatement("SELECT * FROM produit where label=? ");
            myStmt.setString(1, prod);
            ResultSet rs = myStmt.executeQuery();
            while(rs.next())
            {
                String caracteristiques = rs.getString("caracteristiques");
                String description = rs.getString("description");
                String etat = rs.getString("etat");
                String id_categorie = rs.getString("id_categorie");
                Integer id_propietaire = rs.getInt("id_propietaire");
                String nom_image = rs.getString("nom_image");
                Double poid = rs.getDouble("poid");
                Double prix_ancien = rs.getDouble("prix_ancien");
                Double prix_nouv = rs.getDouble("prix_nouv");
                Integer quantite = rs.getInt("quantite");
                Double vote= rs.getDouble("vote");
                String label = rs.getString("label");
                
                
                Produit P =new Produit(caracteristiques,description,etat,id_categorie,id_propietaire,nom_image,poid,prix_ancien,prix_nouv,quantite,vote,label);
                liste.add(P);
            }
            
            return liste;
        } catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

            return liste;
    }
   public Produit findById(int id) {
		String req="SELECT * FROM Produit WHERE id='"+id+"'" ;
        ResultSet rs;
        Produit p=null;
		try {
			rs = cnx.createStatement().executeQuery(req);
		
       
        while(rs.next()){
           p=new Produit();
            
           p.setPrix_nouv(rs.getFloat("prix_nouv"));
           p.setLabel(rs.getString("label"));
            p.setQuantite(rs.getInt("quantite"));
            p.setNom_image(rs.getString("nom_image"));           
           p.setId_categorie(rs.getString("id_categorie"));
           p.setEtat(rs.getString("etat"));

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return p;
    
		
}
}
