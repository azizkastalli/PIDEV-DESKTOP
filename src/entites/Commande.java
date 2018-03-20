/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author iheb bf
 */
public class Commande {
    
    private int id;
    private int nbr_totale;
    private double prix_totale;
    private Date date;
    private Boolean etat;

    public Commande(int nbr_totale, double prix_totale, Date date, Boolean etat) {
        this.nbr_totale = nbr_totale;
        this.prix_totale = prix_totale;
        this.date = date;
        this.etat = etat;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    

    public int getNbr_totale() {
        return nbr_totale;
    }

    public void setNbr_totale(int nbr_totale) {
        this.nbr_totale = nbr_totale;
    }

    public double getPrix_totale() {
        return prix_totale;
    }

    public void setPrix_totale(double prix_totale) {
        this.prix_totale = prix_totale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
    
    
    
    
}
