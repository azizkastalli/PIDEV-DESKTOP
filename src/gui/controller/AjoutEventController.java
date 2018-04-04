/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Categorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudCategorie;
import services.CrudEvenement;
import entites.Evenement;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class AjoutEventController implements Initializable {

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
    private Label espace;
    @FXML
    private FontAwesomeIconView addstoreicon;
    @FXML
    private FontAwesomeIconView addenchersicon;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField Nom;
    @FXML
    private TextField nom_image;
    private TextField nombre;
  
    @FXML
    private TextField description;
    @FXML
 private Spinner<Integer> spin;
    @FXML
    private ComboBox catégorie;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           final int initialValue = 10;
        SpinnerValueFactory<Integer> valueFactory =// 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 50000, initialValue);
          spin.setValueFactory(valueFactory);
          CrudCategorie CC=new CrudCategorie();
            
            
            ArrayList  arrayList;
        
        try {
            arrayList = (ArrayList) CC.ExtractNom();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);
            catégorie.setItems(observableList);
        // TODO
        } catch (SQLException ex) {
        }
            
            
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
    private void AjouterEvenement(ActionEvent event) throws ParseException, IOException, SQLException {
        
        
         CrudEvenement CE=new CrudEvenement();
            CrudCategorie CC=new CrudCategorie();
        String nom=Nom.getText();
        String Des=description.getText();
        
       int Nombre= spin.getValue();
   
       SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date parsed = format.parse(date_debut.getEditor().getText());
        java.sql.Date Date_d = new java.sql.Date(parsed.getTime());
        
    String a=(String) catégorie.getValue();
   int id_categorie= CC.ExtractId(a);
 
        Date parsed1 = format.parse(date_fin.getEditor().getText());
        java.sql.Date Date_f = new java.sql.Date(parsed.getTime());
        
        String image=nom_image.getText();
        System.err.println(Date_d);
       Evenement E=new  Evenement(nom, Des, Nombre, Date_d, Date_f,false, image,id_categorie);
        System.out.println(E.toString());
        try {
            CE.Create(E);
        } catch (SQLException ex) {
        }
        
      
      
       
                new Alert(Alert.AlertType.INFORMATION, "Evenement Ajouté").show();
    }
}
