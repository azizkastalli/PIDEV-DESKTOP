/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudProduit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterStoreController implements Initializable {

    @FXML
    private VBox parent;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;
    @FXML
    private Label veterinaires;
    @FXML
    private Label evenements;
    @FXML
    private FontAwesomeIconView addstoreicon;
    @FXML
    private FontAwesomeIconView addenchersicon;
    @FXML
    private FontAwesomeIconView gererproduiticon;
    @FXML
    private FontAwesomeIconView gererenchersicon;
    @FXML
    private Label espace;
    @FXML
    private TextField nomproduit;
    @FXML
    private TextField caracteristique;
    @FXML
    private TextField description;
    @FXML
    private TextField etat;
    @FXML
    private TextField categorie;
    @FXML
    private TextField proprietaire;
    @FXML
    private TextField vote;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prixnouv;
    @FXML
    private TextField prixanc;
    @FXML
    private TextField poid;
    @FXML
    private TextField image;
    @FXML
    private Button ajoutproduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }

    @FXML
    private void MenuEspace(MouseEvent event) {
           MenuController menu = new MenuController();
        menu.GestionMenuEspace(event);
    }

    @FXML
    private void ajoutproduit(ActionEvent event) throws IOException {
        CrudProduit CP = new CrudProduit();
        Produit PR = new Produit();
        PR.setLabel(nomproduit.getText());
        PR.setCaracteristiques(caracteristique.getText());
        PR.setDescription(description.getText());
        PR.setEtat(etat.getText());
        int idcat= Integer.parseInt(categorie.getText());
        PR.setId_categorie(idcat);
        int idprop = Integer.parseInt(proprietaire.getText()) ;
        PR.setId_propietaire(idprop);
        PR.setNom_image(image.getText());
        double poi=Double.parseDouble(poid.getText());
        PR.setPoid(poi);
        double prian=Double.parseDouble(prixanc.getText());
        double prinouv=Double.parseDouble(prixnouv.getText());
        PR.setPrix_ancien(prian);
        PR.setPrix_nouv(prinouv);
        int quant= Integer.parseInt(quantite.getText());
        PR.setQuantite(quant);
        double vot=Double.parseDouble(vote.getText());
        PR.setVote(vot);
        
        
        
        
         try {
            CP.Create(PR);
             System.out.println("produit ajouté");
        } catch (SQLException ex) {
        }
        
          
    }
    
    

    
    
        
        
        
    
}
