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
public class Favoris {
   private int id;
   private int id_client;
   private int id_produit;

    public Favoris(){}

    public Favoris(int id, int id_client, int id_produit) {
        this.id = id;
        this.id_client = id_client;
        this.id_produit = id_produit;
    }
    
    public Favoris(int id_client, int id_produit) {
        this.id_client = id_client;
        this.id_produit = id_produit;
    }

    public int getId() {
        return id;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
   
   
}
