/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author azizkastalli
 */
public class Session {
  private double derniere_mise;
  private String etat;
  private int id;
  private String id_gagnant;
  private CheckBox checkbox = new CheckBox();
  private ImageView image;
  private String NomProduit;
  private String gagnant;
  private String img;
  
    public Session(){}
  
    //constructeur pour select ou delete
    public Session(int id, String etat,double derniere_mise,String img,String NomProduit,String gagnant) {
        this.id=id;
        this.derniere_mise = derniere_mise;
        this.etat = etat;
        this.NomProduit=NomProduit;
        this.gagnant=gagnant;
        this.image= new ImageView(new Image("/utils/assets/"+img));
    }
    
    //constructeru pour update ou add 
    public Session(double derniere_mise, String etat, String id_gagnant) {
        this.derniere_mise = derniere_mise;
        this.etat = etat;
        this.id_gagnant = id_gagnant;
    }

    public double getDerniere_mise() {
        return derniere_mise;
    }

    public String getEtat() {
        return etat;
    }

    public int getId() {
        return id;
    }

    public String getId_gagnant() {
        return id_gagnant;
    }

    public void setDerniere_mise(double derniere_mise) {
        this.derniere_mise = derniere_mise;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setId_gagnant(String id_gagnant) {
        this.id_gagnant = id_gagnant;
    }

    public void setId(int id) {
        this.id = id;
    }
   
       public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public String getGagnant() {
        return gagnant;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public void setGagnant(String gagnant) {
        this.gagnant = gagnant;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
   
}
