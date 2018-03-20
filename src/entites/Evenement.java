/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import java.util.TimeZone;
import javax.print.DocFlavor;
import java.util.Timer;

/**
 *
 * @author iheb bf
 */
public class Evenement {
    
   private int id;
   private String nom;
   private String description;
   private int nbr_participants;
   private Date date_debut;
   private Date date_fin;
   private Boolean etat;
   private String nom_image;

    public Evenement(){}

    public Evenement(String nom, String description, int nbr_participants, Date date_debut, Date date_fin, Boolean etat, String nom_image) {
        this.nom = nom;
        this.description = description;
        this.nbr_participants = nbr_participants;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.nom_image = nom_image;
    }

    public Evenement(int id, String nom, String description, int nbr_participants, Date date_debut, Date date_fin, Boolean etat, String nom_image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.nbr_participants = nbr_participants;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.nom_image = nom_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr_participants() {
        return nbr_participants;
    }

    public void setNbr_participants(int nbr_participants) {
        this.nbr_participants = nbr_participants;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", nbr_participants=" + nbr_participants + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", etat=" + etat + ", nom_image=" + nom_image + '}';
    }
  

   



    
    
    
}