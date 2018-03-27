/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Encheres;
import entites.Produit;
import java.net.URL;
import java.sql.SQLException;
import services.ServiceEncheres;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.CrudEncheres;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class EncheresController implements Initializable {

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
    private ScrollPane anchorGridPane;
    private final GridPane GP = new GridPane();
    ServiceEncheres SE = new ServiceEncheres();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        //init
        ArrayList <Produit>liste = new ArrayList<Produit>();
        int j=0;
        int i=0;    
    
         //setting up the GridPane
        GP.setPadding(new Insets(10,10,10,10));
        GP.setHgap(5);
        GP.setVgap(5);
        
        //select DATA from DB
        try {
            liste =  SE.Select9();
        } catch (SQLException ex) {
            Logger.getLogger(EncheresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //put DATA
        for(Produit Prd : liste)
        {   
            
            ImageView IV= new ImageView();
            Image I = new Image("/utils/assets/aaa.jpg");
            Pane P = new Pane();
            
            IV.setImage(I);
            IV.setFitWidth(246);
            IV.setFitHeight(277);
            
            P.getChildren().add(IV);            
            GP.add(P,j,i);            

            j++;
            if(j>2)
            {i++;
            j=0;}
           
           }
        
          anchorGridPane.setContent(GP);
          
        }
        
      
    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);   
    }


    @FXML
    private void ChargerPage(ScrollEvent event) {
        
        if(event.getTextDeltaY()<0)
        {
          // add 6 other items in the GridPane here !!!
        }

    }
    
}
