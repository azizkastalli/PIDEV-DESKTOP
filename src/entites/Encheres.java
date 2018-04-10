/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.scene.control.CheckBox;
import java.sql.Timestamp;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author azizkastalli
 */
public class Encheres { 
   private String caracteristiques;
   private String description;
   private String categorie;
   private String nom_proprietaire;
   private String nom_image;
   private double poid;
   private String label;
   private int    id_encheres;
   private int    id_cible;
   private double seuil_mise;
   private String etat;
   private Timestamp   date_debut;
   private CheckBox checkbox;
   private ImageView IV;
   private JFXDatePicker date_debutFX;
   private JFXTimePicker heureFX;
  
   //constructeur vide
    public Encheres() {}
 
    //constructeur pour afficher les encheres
    public Encheres(String caracteristiques, String description, String categorie, String nom_proprietaire, String nom_image, double poid, String label, int id_encheres, double seuil_mise, Timestamp date_debut) {
        this.caracteristiques = caracteristiques;
        this.description = description;
        this.categorie = categorie;
        this.nom_proprietaire = nom_proprietaire;
        this.poid = poid;
        this.label = label;
        this.nom_image=nom_image;
        this.id_encheres = id_encheres;
        this.seuil_mise = seuil_mise;
        this.date_debut = date_debut;
        this.checkbox = new CheckBox();
        this.IV = new ImageView(new Image("/utils/assets/"+nom_image));
        this.date_debutFX= new  JFXDatePicker(date_debut.toLocalDateTime().toLocalDate());
        this.heureFX = new JFXTimePicker(date_debut.toLocalDateTime().toLocalTime());
    }
    
    public Encheres(String etat,String caracteristiques, String description, String categorie, String nom_proprietaire, String nom_image, double poid, String label, int id_encheres, double seuil_mise, Timestamp date_debut) {
        this.caracteristiques = caracteristiques;
        this.description = description;
        this.categorie = categorie;
        this.nom_proprietaire = nom_proprietaire;
        this.poid = poid;
        this.label = label;
        this.nom_image=nom_image;
        this.id_encheres = id_encheres;
        this.seuil_mise = seuil_mise;
        this.date_debut = date_debut;
        this.checkbox = new CheckBox();
        this.IV = new ImageView(new Image("/utils/assets/"+nom_image));
        this.date_debutFX= new  JFXDatePicker(date_debut.toLocalDateTime().toLocalDate());
        this.heureFX = new JFXTimePicker(date_debut.toLocalDateTime().toLocalTime());
        this.etat=etat;
    }

    // quartz constructor
        public Encheres(int id_encheres, Timestamp date_debut,String label) {
        this.id_encheres = id_encheres;
        this.date_debut = date_debut;
        this.label=label;
    }
    
    //constructeur pour ajouter et modifier les encheres
    public Encheres(int id_encheres, int id_cible, double seuil_mise, Timestamp date_debut) {
        this.id_encheres = id_encheres;
        this.id_cible = id_cible;
        this.seuil_mise = seuil_mise;
        this.date_debut = date_debut;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getNom_proprietaire() {
        return nom_proprietaire;
    }

    public String getNom_image() {
        return nom_image;
    }

    public double getPoid() {
        return poid;
    }

    public String getLabel() {
        return label;
    }

    public int getId_encheres() {
        return id_encheres;
    }

    public int getId_cible() {
        return id_cible;
    }

    public double getSeuil_mise() {
        return seuil_mise;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNom_proprietaire(String nom_proprietaire) {
        this.nom_proprietaire = nom_proprietaire;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public void setPoid(double poid) {
        this.poid = poid;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId_encheres(int id_encheres) {
        this.id_encheres = id_encheres;
    }

    public void setId_cible(int id_cible) {
        this.id_cible = id_cible;
    }

    public void setSeuil_mise(double seuil_mise) {
        this.seuil_mise = seuil_mise;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public ImageView getIV() {
        return IV;
    }

    public void setIV(ImageView IV) {
        this.IV = IV;
    }

    public JFXDatePicker getDate_debutFX() {
        return date_debutFX;
    }

    public JFXTimePicker getHeureFX() {
        return heureFX;
    }

    public void setDate_debutFX(JFXDatePicker date_debutFX) {
        this.date_debutFX = date_debutFX;
    }

    public void setHeureFX(JFXTimePicker heureFX) {
        this.heureFX = heureFX;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
  

}
