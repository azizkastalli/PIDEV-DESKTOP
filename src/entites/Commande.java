/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;


/**
 *
 * @author iheb bf
 */
public class Commande {
    
    private int id;
    private double prix_totale;
    private int etat;
    private int id_client;

    public Commande() {}

    public Commande(int id, double prix_totale,int id_client, int etat) {
        this.id = id;
        this.id_client = id_client;
        this.prix_totale = prix_totale;
        this.etat = etat;
    }
    
    public Commande(double prix_totale, int id_client, int etat) {
        this.prix_totale = prix_totale;
        this.etat = etat;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    



    public double getPrix_totale() {
        return prix_totale;
    }

    public void setPrix_totale(double prix_totale) {
        this.prix_totale = prix_totale;
    }


    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    
    
    
}
