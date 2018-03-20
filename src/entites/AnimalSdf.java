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
public class AnimalSdf {
    private int id;
    private String sexe;
    private Date date_trouvaille;
    private String lieu_trouvaille;
    private int id_client;

    public int getId() {
        return id;
    }
    private String nom_image;


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public Date getDate_trouvaille() {
        return date_trouvaille;
    }

    public void setDate_trouvaille(Date date_trouvaille) {
        this.date_trouvaille = date_trouvaille;
    }

    public String getLieu_trouvaille() {
        return lieu_trouvaille;
    }

    public void setLieu_trouvaille(String lieu_trouvaille) {
        this.lieu_trouvaille = lieu_trouvaille;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
   

    public AnimalSdf( String sexe, String nom_image, Date date_trouvaille, String lieu_trouvaille, int id_client) {
        
        this.sexe = sexe;
        this.nom_image = nom_image;
        this.date_trouvaille = date_trouvaille;
        this.lieu_trouvaille = lieu_trouvaille;
        this.id_client = id_client;
    }

    public AnimalSdf() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final AnimalSdf other = (AnimalSdf) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnimalSdf{"  + ", sexe=" + sexe + ", date_trouvaille=" + date_trouvaille + ", lieu_trouvaille=" + lieu_trouvaille + ", id_client=" + id_client + ", nom_image=" + nom_image + '}';
    }
    
  
}
