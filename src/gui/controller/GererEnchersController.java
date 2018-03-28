/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Encheres;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.CrudEncheres;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GererEnchersController implements Initializable {
    
    private CrudEncheres encheres =new CrudEncheres();
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
    private TableColumn<Encheres, Timestamp> date;
    @FXML
    private TableColumn<Encheres,String> delete;
    @FXML
    private TableColumn<Encheres, ImageView> image;
    @FXML
    private TableColumn<Encheres, String> label;
    @FXML
    private TableColumn<Encheres, String> categorie;
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudEncheres encheres =new CrudEncheres();
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
        date.setCellValueFactory(new PropertyValueFactory<Encheres,Timestamp>("date_debut"));
        delete.setCellValueFactory(new PropertyValueFactory<Encheres,String>("checkbox"));
        image.setCellValueFactory(new PropertyValueFactory<Encheres,ImageView>("IV"));
        label.setCellValueFactory(new PropertyValueFactory<Encheres,String>("label"));
        categorie.setCellValueFactory(new PropertyValueFactory<Encheres,String>("categorie"));

        table.setEditable(true);
        
        // id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         seuil.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      //   date.setCellFactory
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
        } catch (SQLException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

}
