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
    
  private  int    etat;
  private  int    id;
  private  int    id_client;
  private  double prix_tot;

    public Commande() {
    }

    public Commande(int etat, int id, int id_client, double prix_tot) {
        this.etat = etat;
        this.id = id;
        this.id_client = id_client;
        this.prix_tot = prix_tot;
    }

    public Commande(int etat, int id_client, double prix_tot) {
        this.etat = etat;
        this.id_client = id_client;
        this.prix_tot = prix_tot;
    }

    public int getEtat() {
        return etat;
    }

    public int getId() {
        return id;
    }

    public int getId_client() {
        return id_client;
    }

    public double getPrix_tot() {
        return prix_tot;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setPrix_tot(double prix_tot) {
        this.prix_tot = prix_tot;
    }

    
}