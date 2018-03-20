/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author user
 */
public class Rdv_Dresseur {
    private int id;
    private int id_client;
    private String coordonnees;
    private int id_animal;
    private Date date_rdv;
    private Time duree_seance;
    private boolean etat;
    private int id_dresseur;

    public Rdv_Dresseur() {}
    
    public Rdv_Dresseur(int id_client, String coordonnees, int id_animal, Date date_rdv, Time duree_seance, boolean etat, int id_dresseur) {
        this.id_client = id_client;
        this.coordonnees = coordonnees;
        this.id_animal = id_animal;
        this.date_rdv = date_rdv;
        this.duree_seance = duree_seance;
        this.etat = etat;
        this.id_dresseur = id_dresseur;
    }

    public Rdv_Dresseur(int id, int id_client, String coordonnees, int id_animal, Date date_rdv, Time duree_seance, boolean etat, int id_dresseur) {
        this.id = id;
        this.id_client = id_client;
        this.coordonnees = coordonnees;
        this.id_animal = id_animal;
        this.date_rdv = date_rdv;
        this.duree_seance = duree_seance;
        this.etat = etat;
        this.id_dresseur = id_dresseur;
    }
    
    

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public Date getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(Date date_rdv) {
        this.date_rdv = date_rdv;
    }

    public Time getDuree_seance() {
        return duree_seance;
    }

    public void setDuree_seance(Time duree_seance) {
        this.duree_seance = duree_seance;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getId_dresseur() {
        return id_dresseur;
    }

    public void setId_dresseur(int id_dresseur) {
        this.id_dresseur = id_dresseur;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Rdv_Dresseur other = (Rdv_Dresseur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rdv_Dresseur{" + "id=" + id + ", id_client=" + id_client + ", coordonnees=" + coordonnees + ", id_animal=" + id_animal + ", date_rdv=" + date_rdv + ", duree_seance=" + duree_seance + ", etat=" + etat + ", id_dresseur=" + id_dresseur + '}';
    }    
    
}
