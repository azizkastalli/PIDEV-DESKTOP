/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
import java.sql.Time;



/**
 *
 * @author rayen
 */
public class Commentaire {

  private Date date;
  private int id;
  private int id_cible;
  private int id_client;
  private Time temps;
  private String texte;
    
            
    public Commentaire(){}

    public Commentaire(Date date, int id, int id_cible, int id_client, Time temps, String texte) {
        this.date = date;
        this.id = id;
        this.id_cible = id_cible;
        this.id_client = id_client;
        this.temps = temps;
        this.texte = texte;
    }

    public Commentaire(Date date, int id_cible, int id_client, Time temps, String texte) {
        this.date = date;
        this.id_cible = id_cible;
        this.id_client = id_client;
        this.temps = temps;
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getId_cible() {
        return id_cible;
    }

    public int getId_client() {
        return id_client;
    }

    public Time getTemps() {
        return temps;
    }

    public String getTexte() {
        return texte;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_cible(int id_cible) {
        this.id_cible = id_cible;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setTemps(Time temps) {
        this.temps = temps;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
    
  
    
}
