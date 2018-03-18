/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;
import java.util.Date;
/**
 *
 * @author azizkastalli
 */
public class Encheres {
    private int    id_encheres;
    private double seuil_mise;
    private String id_proprietaire;
    private String id_cible;
    private Date   date_debut;

    public Encheres(int id_encheres,double seuil_mise, String id_proprietaire, String id_cible, Date date_debut) {
        this.id_encheres=id_encheres;
        this.seuil_mise = seuil_mise;
        this.id_proprietaire = id_proprietaire;
        this.id_cible = id_cible;
        this.date_debut = date_debut;
    }

    public void setId_encheres(int id_encheres) {
        this.id_encheres = id_encheres;
    }

    public int getId_encheres() {
        return id_encheres;
    }

    public void setId_encheres(int id_encheres) {
        this.id_encheres = id_encheres;
    }

    public double getSeuil_mise() {
        return seuil_mise;
    }

    public String getId_proprietaire() {
        return id_proprietaire;
    }

    public String getId_cible() {
        return id_cible;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setSeuil_mise(double seuil_mise) {
        this.seuil_mise = seuil_mise;
    }

    public void setId_proprietaire(String id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public void setId_cible(String id_cible) {
        this.id_cible = id_cible;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }
    
}
