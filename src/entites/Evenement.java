/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.sql.Date;
import java.sql.Timestamp;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author iheb bf
 */
public class Evenement {
    
  
   private int id;
   private String nom;
   private String description;
   private int nbr_participants;
   private Timestamp date_debut;
   private Timestamp date_fin;
   private Boolean etat;
   private String nom_image;
   private String nom_association;
   private ImageView IV;
   
   public Button butt;


   private int id_association;
   private int id_categorie;
   
    public Evenement(){}

  

    public Evenement(String nom, String description, int nbr_participants, Timestamp date_debut, Timestamp date_fin, Boolean etat, String nom_image,int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.nbr_participants = nbr_participants;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.nom_image = nom_image;
     
        this.id_categorie=id_categorie;
       
        
       
       
       
    }
     public Evenement(String nom, String description, int nbr_participants, Boolean etat, String nom_image,int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.nbr_participants = nbr_participants;
        
        this.etat = etat;
        this.nom_image = nom_image;
     
        this.id_categorie=id_categorie;
       
        
       
       
       
    }
    
    
      public Evenement(int id,String nom, String description, int nbr_participants, Timestamp date_debut, Timestamp date_fin, Boolean etat, String nom_image,int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.nbr_participants = nbr_participants;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.nom_image = nom_image;
     
        this.id_categorie=id_categorie;
        
           IV = new ImageView(new Image("/utils/assets/"+nom_image));
        IV.setFitHeight(50);
IV.setFitWidth(50);
      if(etat==true){
       butt=new Button("autorise");
      butt.setStyle("-fx-background-color: #00EC00");
       butt.setTextFill(Color.WHITE);}
      else  if(etat==false){
                  butt =new Button("non autorise");
      butt.setStyle("-fx-background-color: #ff0800");
      
    butt.setTextFill(Color.WHITE);
      
     }

          
    }

 
    
    
      public ImageView getIV() {
        return IV;
    }

    public void setIV(ImageView IV) {
        this.IV = IV;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getNbr_participants() {
        return nbr_participants;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }

    public Boolean getEtat() {
        return etat;
    }

    public String getNom_image() {
        return nom_image;
    }

    public String getNom_association() {
        return nom_association;
    }

  

    public int getId_association() {
        return id_association;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbr_participants(int nbr_participants) {
        this.nbr_participants = nbr_participants;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public void setNom_association(String nom_association) {
        this.nom_association = nom_association;
    }

   

    public void setId_association(int id_association) {
        this.id_association = id_association;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

   

  
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "nom=" + nom + ", description=" + description + ", nbr_participants=" + nbr_participants + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", etat=" + etat + ", nom_image=" + nom_image + ", nom_association=" + nom_association + '}';
    }

  
   private JFXDatePicker date_debutFX;
   private JFXTimePicker heureFX;

    public JFXDatePicker getDate_debutFX() {
        return date_debutFX;
    }

    public void setDate_debutFX(JFXDatePicker date_debutFX) {
        this.date_debutFX = date_debutFX;
    }

    public JFXTimePicker getHeureFX() {
        return heureFX;
    }

    public void setHeureFX(JFXTimePicker heureFX) {
        this.heureFX = heureFX;
    }

    public Button getButt() {
        return butt;
    }

    public void setButt(Button butt) {
        this.butt = butt;
    }

    
   



    
    
    
}