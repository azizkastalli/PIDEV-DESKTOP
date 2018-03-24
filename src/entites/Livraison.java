/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author user
 */
public class Livraison {

   private int  etat;
   private int  id;
   private int  id_client;
   private int  id_commande;
   private int  id_livreur;

    public Livraison() {}

    public Livraison(int etat, int id, int id_client, int id_commande, int id_livreur) {
        this.etat = etat;
        this.id = id;
        this.id_client = id_client;
        this.id_commande = id_commande;
        this.id_livreur = id_livreur;
    }

    public Livraison(int etat, int id_client, int id_commande, int id_livreur) {
        this.etat = etat;
        this.id_client = id_client;
        this.id_commande = id_commande;
        this.id_livreur = id_livreur;
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

    public int getId_commande() {
        return id_commande;
    }

    public int getId_livreur() {
        return id_livreur;
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

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }
  

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livraison other = (Livraison) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
