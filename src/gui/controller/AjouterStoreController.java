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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import services.CrudProduit;
import static gui.controller.LoginController.loggduser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
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
     @FXML
    private ComboBox<String> combobox;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.getItems().addAll("chien","chat","oiseaux","hamster");
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
        PR.setId_categorie(combobox.getValue());
        PR.setId_propietaire(loggduser.getId());
        PR.setNom_image(image.getText());
        double poi=Double.parseDouble(poid.getText());
        PR.setPoid(poi);
        double prian=Double.parseDouble(prixanc.getText());
        PR.setPrix_ancien(0);
        PR.setPrix_nouv(prian);
        int quant= Integer.parseInt(quantite.getText());
        PR.setQuantite(quant);
        PR.setVote(0);
        
        
        
        
         try {
            CP.Create(PR);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("I have a great message for you!");
            alert1.setHeaderText(null);
            alert1.setContentText("Ajout réussit !");
            alert1.showAndWait();
           Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/acceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show(); 
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
