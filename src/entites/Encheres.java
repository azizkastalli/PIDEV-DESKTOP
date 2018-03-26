/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import javafx.scene.control.CheckBox;
import java.sql.Timestamp;

/**
 *
 * @author azizkastalli
 */
public class Encheres {
    private int    id_encheres;
    private double seuil_mise;
    private int id_proprietaire;
    private int id_cible;
    private Timestamp   date_debut;
    private CheckBox checkbox;
   
    public Encheres(){
            this.checkbox = new CheckBox();    
}
    
    public Encheres(int id_encheres) {
        this.id_encheres=id_encheres;
        this.checkbox = new CheckBox();    
    }

    
    public Encheres(int id_encheres,double seuil_mise, Timestamp date_debut) {
        this.id_encheres=id_encheres;
        this.seuil_mise = seuil_mise;
        this.date_debut = date_debut;
        this.checkbox = new CheckBox();
    }
    
    public Encheres(int id_encheres,double seuil_mise, int id_proprietaire, int id_cible, Timestamp date_debut) {
        this.id_encheres=id_encheres;
        this.seuil_mise = seuil_mise;
        this.id_proprietaire = id_proprietaire;
        this.id_cible = id_cible;
        this.date_debut = date_debut;
        this.checkbox = new CheckBox();
    }

    public void setId_encheres(int id_encheres) {
        this.id_encheres = id_encheres;
    }

    public int getId_encheres() {
        return id_encheres;
    }

    public double getSeuil_mise() {
        return seuil_mise;
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public int getId_cible() {
        return id_cible;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public void setSeuil_mise(double seuil_mise) {
        this.seuil_mise = seuil_mise;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public void setId_cible(int id_cible) {
        this.id_cible = id_cible;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

}
