/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import static gui.controller.LoginController.loggduser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class RubriqueServicesController implements Initializable {

    @FXML
    private Label anp;
    @FXML
    private ImageView animp;
    @FXML
    private Label anisdf;
    @FXML
    private ImageView rdv;
    @FXML
    private Label espace;
    @FXML
    private ImageView ans;
    @FXML
    private Label rdvs;

     public RubriqueServicesController(){}
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

    
    
}
