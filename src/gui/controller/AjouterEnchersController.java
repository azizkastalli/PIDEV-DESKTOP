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
import entites.Session;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudEncheres;
import services.CrudSession;

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
    private TableColumn<Produit, ImageView> image;
    @FXML
    private TableColumn<Produit, String> label;
    @FXML
    private TableColumn<Produit, Double> prix;
    @FXML
    private TableColumn<Produit, Integer> qte;
    @FXML
    private TableView<Produit> table;
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
        
        image.setCellValueFactory(new PropertyValueFactory<Produit,ImageView>("image"));
        label.setCellValueFactory(new PropertyValueFactory<Produit,String>("label"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix_nouv"));
        qte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
                
                
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
        tfmise.setDisable(false);
        date.setDisable(false);
        heure.setDisable(false);
        Ajout.setDisable(false);
    }

    @FXML
    private void AjoutFinalStep(MouseEvent event) {
      CrudEncheres CE = new CrudEncheres();
      CrudSession CS = new CrudSession();
      Session S = new Session();
      
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
           
            E.setId_encheres(0);
            E=CE.Select(E);
            
            S.setId(E.getId_encheres());
            S.setEtat("en attente");
            S.setId_gagnant("");
            S.setDerniere_mise(0);
      
            CS.Create(S);
            
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(AjouterEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
}