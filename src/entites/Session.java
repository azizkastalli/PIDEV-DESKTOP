/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author azizkastalli
 */
public class Session {
  private double derniere_mise;
  private String etat;
  private int id;
  private String id_gagnant;

    public Session(){}
  
    public Session(int id, String etat,double derniere_mise, String id_gagnant) {
        this.id=id;
        this.derniere_mise = derniere_mise;
        this.etat = etat;
        this.id_gagnant = id_gagnant;
    }
    
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
   
   
}
