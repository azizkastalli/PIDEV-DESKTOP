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
    private int id;
    private String texte;
    private Date myDate ;
    private String client;
    private int id_cible;

    public Commentaire(){}
    
    public Commentaire(int id, String texte, Date myDate, String client, int id_cible) {
        this.id = id;
        this.texte = texte;
        this.myDate = myDate;
        this.client = client;
        this.id_cible = id_cible;
    }
    
    public Commentaire(String texte, String client, int id_cible) {
        this.texte = texte;
        this.client = client;
        this.id_cible = id_cible;
    }
    
    
    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }

    
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getId_cible() {
        return id_cible;
    }
    public void setId_cible(int id_cible) {
        this.id_cible = id_cible;
    }


    


    
}
