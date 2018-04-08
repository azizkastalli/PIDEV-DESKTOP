/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Encheres;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import services.ServiceEncheres;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private int nbr=9;
    private int j=0;
    private int i=0;    
    private int scroll=0;   
    private ArrayList <Encheres>liste = new ArrayList<Encheres>();
    private ArrayList<Pane> ListePane = new ArrayList<>();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
      

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
        for(Encheres E : liste)
        {   
            //image set
            ImageView IV= new ImageView();
            Image I = new Image("/utils/assets/"+E.getNom_image());
            IV.setImage(I);
            IV.setFitWidth(230);
            IV.setFitHeight(177);
            IV.setLayoutX(6);
            
            //pane set
            Pane P = new Pane();
            P.setId(Integer.toString(E.getId_encheres()));
            P.setPrefWidth(246);
            P.setPrefHeight(277);
            P.setStyle("-fx-background-color: #4f86dd");
            //label set 
            Label L= new Label(E.getLabel());
              L.setLayoutY(185);
              L.setLayoutX(50);
              L.setStyle("-fx-font-size:20");
              //L.setStyle("-fx-font-weight:bold");
              //L.setStyle("-fx-text-fill: black");
              L.setPrefHeight(25);
              L.setPrefWidth(246);
                          
           CountDownController countdown = new CountDownController();
           countdown.init(E.getDate_debut());
           VBox vb = countdown.setgui();
           vb.setLayoutY(215);
           vb.setLayoutX(0);
                   
            P.getChildren().addAll(IV,L,vb);            
            ListePane.add(P);
            GP.add(P,j,i);            

            j++;
            if(j>2)
            {i++;
            j=0;}
           
           }
        
          anchorGridPane.setContent(GP);
         
       //on scroll end add some data
      
              anchorGridPane.setOnScroll(e -> {

                scroll ++;

                Thread th = new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        Platform.runLater(() -> {
                            if(scroll == 1) 
                             AddDataOnScroll(e);
                                scroll--;
                        });
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
                th.setDaemon(true);
                th.start();
            });

 
  GP.setOnMouseClicked((MouseEvent event) -> {
      ListePane.forEach((P) -> { 
          P.setOnMouseClicked((event2) -> {
              RedirectToDetailEncheres(P);
             });
         });
      }); 
    
    }
      
      
    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);   
    }
    
   
 private void AddDataOnScroll(ScrollEvent event)   
 {
     ArrayList <Encheres>liste = new ArrayList<Encheres>();
  
        
        if(event.getTextDeltaY()<0)
        {
        try {
            liste =  SE.Select6(nbr);
        } catch (SQLException ex) {
            Logger.getLogger(EncheresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //put DATA in GridPane
        for(Encheres E : liste)
        {   
                       //image set
            ImageView IV= new ImageView();
            Image I = new Image("/utils/assets/"+E.getNom_image());
            IV.setImage(I);
            IV.setFitWidth(230);
            IV.setFitHeight(177);
            IV.setLayoutX(6);
            
            //pane set
            Pane P = new Pane();
            P.setId(Integer.toString(E.getId_encheres()));
            P.setPrefWidth(246);
            P.setPrefHeight(277);
            P.setStyle("-fx-background-color: #4f86dd");
            //label set 
            Label L= new Label(E.getLabel());
              L.setLayoutY(185);
              L.setLayoutX(50);
              L.setStyle("-fx-font-size:20");
              //L.setStyle("-fx-font-weight:bold");
              //L.setStyle("-fx-text-fill: black");
              L.setPrefHeight(25);
              L.setPrefWidth(246);
                          
           CountDownController countdown = new CountDownController();
           countdown.init(E.getDate_debut());
           VBox vb = countdown.setgui();
           vb.setLayoutY(215);
           vb.setLayoutX(0);
                   
            P.getChildren().addAll(IV,L,vb);            
            ListePane.add(P);
            GP.add(P,j,i);      

            j++;
            if(j>2)
            {i++;
            j=0;}
           
           }     
      nbr=GP.getChildren().size();
      
        }

 }
    
 
     private void RedirectToDetailEncheres(Pane P)
    {
     Parent home_page_parent = null;
     FXMLLoader Loader = new FXMLLoader();
     
     Loader.setLocation(getClass().getResource("/gui/DetailEncheres.fxml"));
     
        try {
            home_page_parent = Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(EncheresController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) P.getScene().getWindow();
        
        
        DetailEncheresController DEC = Loader.getController();  
        DEC.Init(Integer.parseInt(P.getId()));
        
                app_stage.setScene(home_page_scene);
                app_stage.show();  
    }

     
}
