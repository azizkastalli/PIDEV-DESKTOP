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
public class Rdv_Veterinaire {
    private int id;
    private String id_client;
    private String id_vet;
    private Date date_rdv;
    private Date duree_seance;
    private double prix;
    private boolean etat;

    public Rdv_Veterinaire() {
    }

    public Rdv_Veterinaire(String id_client, String id_vet, Date date_rdv, Date duree_seance, double prix, boolean etat) {
        this.id_client = id_client;
        this.id_vet = id_vet;
        this.date_rdv = date_rdv;
        this.duree_seance = duree_seance;
        this.prix = prix;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getId_vet() {
        return id_vet;
    }

    public void setId_vet(String id_vet) {
        this.id_vet = id_vet;
    }

    public Date getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(Date date_rdv) {
        this.date_rdv = date_rdv;
    }

    public Date getDuree_seance() {
        return duree_seance;
    }

    public void setDuree_seance(Date duree_seance) {
        this.duree_seance = duree_seance;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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
        final Rdv_Veterinaire other = (Rdv_Veterinaire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rdv_Veterinaire{" + "id=" + id + ", id_client=" + id_client + ", id_vet=" + id_vet + ", date_rdv=" + date_rdv + ", duree_seance=" + duree_seance + ", prix=" + prix + ", etat=" + etat + '}';
    }
    
}
