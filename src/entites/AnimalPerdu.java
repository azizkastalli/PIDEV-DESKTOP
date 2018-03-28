/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author user
 */
public class AnimalPerdu {
    
    private int id;
    private int id_animal;
    private boolean etat;
    private Date date_disparition;
    private String lieu_disparition; 

    
        public AnimalPerdu() {}

        public AnimalPerdu(int id, int id_animal, boolean etat, Date date_disparition, String lieu_disparition) {
        this.id = id;
        this.id_animal = id_animal;
        this.etat = etat;
        this.date_disparition = date_disparition;
        this.lieu_disparition = lieu_disparition;
    }
    


        public AnimalPerdu( int id_animal, boolean etat, Date date_disparition, String lieu_dispairition) {
        
        this.id_animal = id_animal;
        this.etat = etat;
        this.date_disparition = date_disparition;
        this.lieu_disparition = lieu_dispairition;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AnimalPerdu{" + "id=" + id + ", id_animal=" + id_animal + ", etat=" + etat + ", date_disparition=" + date_disparition + ", lieu_disparition=" + lieu_disparition + '}';
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
        final AnimalPerdu other = (AnimalPerdu) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Date getDate_disparition() {
        return date_disparition;
    }

    public void setDate_disparition(Date date_disparition) {
        this.date_disparition = date_disparition;
    }

    public String getLieu_dispairition() {
        return lieu_disparition;
    }

    public void setLieu_dispairition(String lieu_dispairition) {
        this.lieu_disparition = lieu_dispairition;
    }

}
