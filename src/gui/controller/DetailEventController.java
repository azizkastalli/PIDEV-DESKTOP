/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import static gui.controller.EventClientController.E;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class DetailEventController implements Initializable {

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
    private Label espace;
    @FXML
    private ImageView image1;
    @FXML
    private Label titre;
    @FXML
    private Text tfDescription;
    @FXML
    private Label nbre;
    @FXML
    private Label dateD;
    @FXML
    private Label dateF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
             String a="C:\\Users\\iheb ben fraj\\Desktop\\piJava\\pidesktop1.0\\src\\utils\\assets\\";
            Image img6 = new Image(new FileInputStream( a+E.getNom_image()),393,394, false, false);
            
            image1.setImage(img6);
            tfDescription.setText("Description : "+E.getDescription());
            nbre.setText("Nombre participants restant est :"+E.getNbr_participants());
            dateD.setText("Date Debut :"+E.getDate_debut());
            dateF.setText("Date Fin : "+E.getDate_fin());
            
            titre.setText(E.getNom());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

       @FXML
    private void Menu(MouseEvent event) {
    
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
}
