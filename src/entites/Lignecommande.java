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
public class Lignecommande {
    
    private int id;
    private int id_panier;
    private int id_commande;

    public Lignecommande() {
    }

    public Lignecommande(int id_panier, int id_commande) {
        this.id_panier = id_panier;
        this.id_commande = id_commande;
    }

    public Lignecommande(int id, int id_panier, int id_commande) {
        this.id = id;
        this.id_panier = id_panier;
        this.id_commande = id_commande;
    }

    
    public int getId() {
        return id;
    }

   

    public int getId_panier() {
        return id_panier;
    }

   

    public int getId_commande() {
        return id_commande;
    }

    
    
    
    
}
