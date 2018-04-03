/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Categorie;
import entites.Evenement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudCategorie;
import services.CrudEvenement;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class ModifSuppEventController implements Initializable {

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
    private TableView<Evenement> TV;
    @FXML
    private TableColumn<Evenement,String> NomC;
    @FXML
    private TableColumn<Evenement,String> DescC;
    @FXML
    private TableColumn<Evenement,String> nbrC;
    @FXML
    private TableColumn<Evenement,String> CatC;
    @FXML
    private TableColumn<Evenement,ImageView> imageC;
    @FXML
    private TableColumn<Evenement,Date> DateDC;
    @FXML
    private TableColumn<Evenement,Date> DateFC;
    @FXML
    private ImageView image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
               
        CrudEvenement CE=new CrudEvenement();
        ArrayList<Evenement> arrayList=(ArrayList<Evenement>) CE.SelectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TV.setItems(observableList);
        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("description"));
        nbrC.setCellValueFactory(new PropertyValueFactory<>("nbr_participants"));
        CatC.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        imageC.setCellValueFactory(new PropertyValueFactory<Evenement,ImageView>("IV"));
        DateDC.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        DateFC.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        
      
   
       

         //  Image img1 = new Image(new FileInputStream(EV.getNom_image()), 100,100, false, false);
              
       
      
        
        
    }    
    
        @FXML
    private void Menu(MouseEvent event) {
    
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }

    @FXML
    private void affiche(ActionEvent event) {
      
    }
}
