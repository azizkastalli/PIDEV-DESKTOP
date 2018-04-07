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
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import services.CrudEncheres;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class GererEnchersController implements Initializable {
    CrudEncheres encheres =new CrudEncheres();
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
    private TableView<Encheres> table;
    @FXML
    private TableColumn<Encheres, Double> seuil;
    @FXML
    private TableColumn<Encheres, JFXDatePicker> date;
    @FXML
    private TableColumn<Encheres,String> delete;
    @FXML
    private TableColumn<Encheres, ImageView> image;
    @FXML
    private TableColumn<Encheres, String> label;
    @FXML
    private TableColumn<Encheres, String> categorie;
    @FXML
    private TableColumn<Encheres, JFXTimePicker> heure;
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Encheres E = new Encheres();
                
        ArrayList arrayList=null;
        try {
             arrayList = (ArrayList) encheres.SelectAll();
        } catch (SQLException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        
        seuil.setCellValueFactory(new PropertyValueFactory<Encheres,Double>("seuil_mise"));
        date.setCellValueFactory(new PropertyValueFactory<Encheres,JFXDatePicker>("date_debutFX"));
        heure.setCellValueFactory(new PropertyValueFactory<Encheres,JFXTimePicker>("heureFX"));
        delete.setCellValueFactory(new PropertyValueFactory<Encheres,String>("checkbox"));
        image.setCellValueFactory(new PropertyValueFactory<Encheres,ImageView>("IV"));
        label.setCellValueFactory(new PropertyValueFactory<Encheres,String>("label"));
        categorie.setCellValueFactory(new PropertyValueFactory<Encheres,String>("categorie"));

        table.setEditable(true);
        
         seuil.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
  
                 
                 
   
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
    private void delete(MouseEvent event) {
               ObservableList<Encheres> data = table.getItems();
               ObservableList<Encheres> oblist = FXCollections.observableArrayList();

               for(Encheres E : data)
               {
                  if(E.getCheckbox().isSelected())
                  {
                      try {
                          encheres.Delete(E);
                          oblist.add(E);
                          
                           // alert pour notifier la suppression de l'enchere
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                               alert.setTitle("notification"); 
                               alert.setHeaderText("Encheres supprimé avec succes");
                               alert.setContentText("votre produit vient d'etres supprimé des encheres !");

                               alert.showAndWait(); 
                          
                      } catch (SQLException ex) {
                          Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
               }
             data.removeAll(oblist);
    }
    
    
    @FXML
   private void UpdateMise(TableColumn.CellEditEvent<Encheres, Double> event) {
        Encheres E = new Encheres();
                 E=event.getRowValue();
                 E.setSeuil_mise(event.getNewValue());

            try {
            encheres.Update(E);
                  // alert pour notifier la modification de l'enchere
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("notification"); 
                alert.setHeaderText("Encheres modifié avec succes");
                alert.setContentText("vous avez modifier le seuil de mise de cette enchere avec succes !");

                alert.showAndWait(); 
        } catch (SQLException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Update(MouseEvent event) {
      CrudEncheres CE = new CrudEncheres();
      Encheres E = table.getSelectionModel().getSelectedItem(); 
      E.setSeuil_mise(0);                
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      String dateEncheres = date.getCellData(E).getValue().format(formatter);
      LocalTime LT = heure.getCellData(E).getValue();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date parsedDate = null;    
        try {
            parsedDate = dateFormat.parse(dateEncheres);
        } catch (ParseException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
            parsedDate.setMinutes(LT.getMinute());
            parsedDate.setHours(LT.getHour());  
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            E.setDate_debut(timestamp);    
               
        try {
            CE.Update(E);
                    // alert pour notifier la modification de l'enchere
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("notification"); 
                alert.setHeaderText("Encheres modifié avec succes");
                alert.setContentText("vous avez modifier la date de debut de cette enchere avec succes !");

                alert.showAndWait(); 
        } catch (SQLException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    }
