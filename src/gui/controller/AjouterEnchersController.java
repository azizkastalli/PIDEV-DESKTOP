/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Encheres;
import entites.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudEncheres;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterEnchersController implements Initializable {
    Encheres E = new Encheres();
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
    private TableColumn<Produit, String> image;
    @FXML
    private TableColumn<Produit, String> label;
    @FXML
    private TableColumn<Produit, Integer> categorie;
    @FXML
    private TableColumn<Produit, Double> prix;
    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, Integer> prop;
    @FXML
    private TextField tfmise;
    @FXML
    private Button Ajout;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTimePicker heure;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudEncheres produits =new CrudEncheres();
        ArrayList arrayList=null;
        try {
             arrayList = (ArrayList) produits.SelectAllProduit();
        } catch (SQLException ex) {

        }

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        
        image.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_image"));
        label.setCellValueFactory(new PropertyValueFactory<Produit,String>("label"));
        categorie.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id_categorie"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix_nouv"));
        id.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
        prop.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id_propietaire"));

        tfmise.setDisable(true);
        date.setDisable(true);
        heure.setDisable(true);
        Ajout.setDisable(true);

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
    private void AjoutStep1(MouseEvent event) {
        
        Produit p = table.getSelectionModel().getSelectedItem();
        E.setId_cible(p.getId());
        E.setId_proprietaire(p.getId_propietaire());
        tfmise.setDisable(false);
        date.setDisable(false);
        heure.setDisable(false);
        Ajout.setDisable(false);
    }

    @FXML
    private void AjoutFinalStep(MouseEvent event) {
      CrudEncheres CE = new CrudEncheres();
      E.setSeuil_mise(Double.parseDouble(tfmise.getText()));

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      String dateEncheres = date.getValue().format(formatter);
      
      String time=dateEncheres+" "+heure.getValue()+":00";

      
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dateEncheres);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            E.setDate_debut(timestamp);    
        
            CE.Create(E);
            
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(AjouterEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
      
  
    }
    
}
