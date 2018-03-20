/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author azizkastalli
 */
public class Wallet {
    private int id;
    private int id_user;
    private String devise;
    private double somme;

    public Wallet(int id_user) {
        this.id_user = id_user;
    }
    
     public Wallet(int id,int id_user, String devise, double somme) {
        this.id=id;
        this.id_user = id_user;
        this.devise = devise;
        this.somme = somme;
    }
    
    public Wallet(int id_user, String devise, double somme) {
        this.id_user = id_user;
        this.devise = devise;
        this.somme = somme;
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public String getDevise() {
        return devise;
    }

    public double getSomme() {
        return somme;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
