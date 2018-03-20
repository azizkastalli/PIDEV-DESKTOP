/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author user
 */
public class Livraison {
    private int id;
    private String id_commande;
    private Date date_livraison;
    private double frais_livraison;
    private String adresse_livraison;
    private String id_livreur;
    private String id_client;
    private boolean etat;

    public Livraison(String id_commande, Date date_livraison, double frais_livraison, String adresse_livraison, String id_livreur, String id_client, boolean etat) {
        this.id_commande = id_commande;
        this.date_livraison = date_livraison;
        this.frais_livraison = frais_livraison;
        this.adresse_livraison = adresse_livraison;
        this.id_livreur = id_livreur;
        this.id_client = id_client;
        this.etat = etat;
    }

    public Livraison() {
    }

    public int getId() {
        return id;
    }

    public String getId_commande() {
        return id_commande;
    }

    public void setId_commande(String id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public double getFrais_livraison() {
        return frais_livraison;
    }

    public void setFrais_livraison(double frais_livraison) {
        this.frais_livraison = frais_livraison;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public String getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(String id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
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

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", id_commande=" + id_commande + ", date_livraison=" + date_livraison + ", frais_livraison=" + frais_livraison + ", adresse_livraison=" + adresse_livraison + ", id_livreur=" + id_livreur + ", id_client=" + id_client + ", etat=" + etat + '}';
    }
    
    
    
}
