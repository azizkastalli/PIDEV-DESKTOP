/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Encheres;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudEncheres;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class DetailEncheresController implements Initializable {

    private int id_encheres;
    @FXML
    private VBox parent;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;
    @FXML
    private Label veterinaire;
    @FXML
    private Label evenements;
    @FXML
    private Label espace;
    @FXML
    private ImageView image;
    @FXML
    private ToggleButton btparticipation;
    @FXML
    private Label label;
    @FXML
    private Label date;
    @FXML
    private Label categorie;
    @FXML
    private Label mise;
    @FXML
    private Label poid;
    @FXML
    private Label caracteristiques;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
        @FXML
    private void Menu(MouseEvent event) {
    
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }

    
    
    public void Init(int id_encheres) {
      this.id_encheres = id_encheres;
      CrudEncheres CE = new CrudEncheres();
      Encheres E = new Encheres();
      E.setId_encheres(this.id_encheres);
        try {
            E=CE.Select(E);
        } catch (SQLException ex) {
            Logger.getLogger(DetailEncheresController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      image.setImage(new Image("/utils/assets/"+E.getNom_image())); 
      caracteristiques.setText(E.getCaracteristiques());
      label.setText(E.getLabel());
      description.setText(E.getDescription());
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      date.setText(dateFormat.format(E.getDate_debut()));
      poid.setText(Double.toString(E.getPoid()));
      mise.setText(Double.toString(E.getSeuil_mise()));
      categorie.setText(E.getCategorie());
      
      
    }

    
}
