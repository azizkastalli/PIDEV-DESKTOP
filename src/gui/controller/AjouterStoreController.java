/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Produit;
import static gui.controller.StoreController.P;
import java.io.File;
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
import javax.swing.JFileChooser;
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
    private TextField categorie;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prixanc;
    @FXML
    private TextField poid;
    @FXML
    private TextField image;
    @FXML
    private Button ajoutproduit;
    @FXML
    private Button choose;

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
        PR.setEtat("en attente");
        int idcat= Integer.parseInt(categorie.getText());
        PR.setId_categorie(idcat);
        PR.setId_propietaire(0);
        PR.setNom_image(image.getText());
        double poi=Double.parseDouble(poid.getText());
        PR.setPoid(poi);
        double prian=Double.parseDouble(prixanc.getText());
        PR.setPrix_ancien(prian);
        PR.setPrix_nouv(0);
        int quant= Integer.parseInt(quantite.getText());
        PR.setQuantite(quant);
        PR.setVote(0);
        
        
        
        
         try {
            CP.Create(PR);
             System.out.println("produit ajouté");
        } 
         catch (SQLException ex) {
        }
        
          
    }

    @FXML
    private void Choose(ActionEvent event) {
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String img = f.getAbsolutePath();
        image.setText(img); 
        
    }
    
    

    
    
        
        
        
    
}
