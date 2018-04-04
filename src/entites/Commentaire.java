/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;





/**
 *
 * @author rayen
 */
public class Commentaire {

  private Date date;
  private int id;
  private String id_cible;
  private int id_client;
  private String texte;
    
            
    public Commentaire(){}

    public Commentaire(Date date, String texte) {
        this.date = date;
        this.texte = texte;
    }


    public Commentaire(Date date, String id_cible, int id_client, String texte) {
        this.date = date;
        this.id_cible = id_cible;
        this.id_client = id_client;
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getId_cible() {
        return id_cible;
    }

    public int getId_client() {
        return id_client;
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

    public void setId_cible(String id_cible) {
        this.id_cible = id_cible;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    

    public void setTexte(String texte) {
        this.texte = texte;
    }
    
  
    
}
