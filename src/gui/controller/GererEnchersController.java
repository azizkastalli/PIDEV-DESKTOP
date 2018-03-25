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
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import services.CrudEncheres;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GererEnchersController implements Initializable {

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
    private TableColumn<Encheres, Integer> id;
    @FXML
    private TableColumn<Encheres, String> idcible;
    @FXML
    private TableColumn<Encheres, String> idprop;
    @FXML
    private TableColumn<Encheres, Timestamp> date;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudEncheres encheres =new CrudEncheres();
        ArrayList arrayList=null;
        try {
             arrayList = (ArrayList) encheres.SelectAll();
        } catch (SQLException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  System.out.println(arrayList.get(0).toString());

       ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        
        seuil.setCellValueFactory(new PropertyValueFactory<Encheres,Double>("seuil_mise"));
        id.setCellValueFactory(new PropertyValueFactory<Encheres,Integer>("id_encheres"));
        idprop.setCellValueFactory(new PropertyValueFactory<Encheres,String>("id_proprietaire"));
        idcible.setCellValueFactory(new PropertyValueFactory<Encheres,String>("id_cible"));
        date.setCellValueFactory(new PropertyValueFactory<Encheres,Timestamp>("date_debut"));
        
         table.setEditable(true);
        
        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       // seuil.setCellFactory(TextFieldTableCell.forTableColumn());
        idprop.setCellFactory(TextFieldTableCell.forTableColumn());
        idcible.setCellFactory(TextFieldTableCell.forTableColumn());
        
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
    private void update(TableColumn.CellEditEvent<Encheres, String> event) {
                CrudEncheres encheres =new CrudEncheres();
                Encheres E = new Encheres();
                E=event.getRowValue();
                E.setId_cible(event.getNewValue());
                System.out.println("prop : "+E.getId_proprietaire()+"id : "+E.getId_encheres()+"cible : "+E.getId_cible());
        try {
            encheres.Update(E);
        } catch (SQLException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
