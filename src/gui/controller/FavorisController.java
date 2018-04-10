/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Favoris;
import entites.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.CrudFavoris;
import services.CrudProduit;
import static gui.controller.LoginController.loggduser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.Math.round;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FavorisController implements Initializable {

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
    private int j=0;
    private int i=0;
    private int k=0;
    private int l=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
            List<Produit> liste = new ArrayList<>();
            List<Favoris> list = new ArrayList<>();
            CrudProduit CP = new CrudProduit();
            CrudFavoris CF = new CrudFavoris();
            ArrayList <Produit> lst_P = new ArrayList<>();
            ArrayList<Pane> ListePane = new ArrayList<>();
            //setting up the GridPane
            GP.setPadding(new Insets(10,10,10,10));
            GP.setHgap(30);
            GP.setVgap(30);
            
            //select DATA from DB
            for (int i = 0; i <CF.Verifyprod(loggduser.getId()).size() ; i++) {
                
                CP.prodfav(CF.Verifyprod(loggduser.getId()).get(i));
                liste.addAll(CP.prodfav(CF.Verifyprod(loggduser.getId()).get(i)));
                
                
            }
            
            //put DATA
            for(Produit E : liste)
            {
               
                
                //image set
                ImageView IV= new ImageView();
                Image I;
                I = new Image("/utils/assets/"+E.getNom_image());
                IV.setImage(I);
                IV.setFitWidth(300);
                IV.setFitHeight(177);
                IV.setLayoutX(6);
                
                
                
                //pane set
                Pane P = new Pane();
                P.setId(Integer.toString(E.getId()));
                P.setPrefWidth(300);
                P.setPrefHeight(277);
                //label set
                Label L= new Label(E.getLabel());
                L.setLayoutY(185);
                L.setLayoutX(100);
                L.setStyle("-fx-font-size:20");
                L.setPrefHeight(25);
                L.setPrefWidth(246);
                //label set
                Label S= new Label(Double.toString(E.getPrix_nouv()));
                S.setLayoutY(220);
                S.setLayoutX(100);
                S.setStyle("-fx-font-size:20");
                S.setPrefHeight(25);
                S.setPrefWidth(246);
                
                
                P.getChildren().addAll(IV,L,S);
                ListePane.add(P);
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

    
}
