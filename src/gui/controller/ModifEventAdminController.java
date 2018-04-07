/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Encheres;
import entites.Evenement;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.CrudEvenement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class ModifEventAdminController implements Initializable {

    @FXML
    private HBox ev;
    @FXML
    private TableView<Evenement> TV;
    @FXML
    private TableColumn<Evenement, String> NomC;
    @FXML
    private TableColumn<Evenement, String> DescC;
    @FXML
    private TableColumn<Evenement, String> nbrC;
    @FXML
    private TableColumn<Evenement, String> CatC;
    @FXML
    private TableColumn<Evenement, ImageView> imageC;
    @FXML
    private TableColumn<Evenement, Date> DateDC;
    @FXML
    private TableColumn<Evenement, Date> DateFC;
    public static Evenement E = new Evenement();
    @FXML
    private TableColumn<Evenement, Button> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudEvenement CE = new CrudEvenement();
        ArrayList<Evenement> arrayList = (ArrayList<Evenement>) CE.SelectAll();

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TV.setItems(observableList);

        etat.setCellValueFactory(new PropertyValueFactory<Evenement,Button>("butt"));
        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("description"));
        nbrC.setCellValueFactory(new PropertyValueFactory<>("nbr_participants"));
        CatC.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("IV"));
        DateDC.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        DateFC.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        TV.setEditable(true);
        
        

    }

    @FXML
    private void HomeClick(ActionEvent event) {
    }

    @FXML
    private void ClickStore(MouseEvent event) {
    }

    @FXML
    private void ClickEvenement(MouseEvent event) {
    }

    @FXML
    private void ClickEncheres(MouseEvent event) {
    }

    @FXML
    private void ClickServices(MouseEvent event) {
    }

    @FXML
    private void ClickVeterinaire(MouseEvent event) {
    }

    @FXML
    private void ClickProduit(MouseEvent event) {
    }

    @FXML
    private void ClickRow(MouseEvent event) {
        
        Button aa=TV.getSelectionModel().getSelectedItem().getButt();
       
      aa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(aa.getText()=="autorise")
                { try {
                     String nom=TV.getSelectionModel().getSelectedItem().getNom();
                Evenement E1=new Evenement();
                
                E1.setEtat(false);
                E1.setNom(nom);
                
                  CrudEvenement CE = new CrudEvenement();
                  
                   
                        CE.Update(E1);
                         
        Parent home_page_parent;
                    try {
                        home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ModifEventAdmin.fxml"));
                         Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
                    } catch (IOException ex) {
                        Logger.getLogger(ModifEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
       
       
                    } catch (SQLException ex) {
                    }
                
                }
                
                   
               else if(aa.getText()=="non autorise")
                {try {
                     String nom=TV.getSelectionModel().getSelectedItem().getNom();
                Evenement E1=new Evenement();
                
                E1.setEtat(true);
                E1.setNom(nom);
                
                  CrudEvenement CE = new CrudEvenement();
                    
                        CE.Update(E1);
                    } catch (SQLException ex) {
                    }
                  Parent home_page_parent;
                    try {
                        home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ModifEventAdmin.fxml"));
                         Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
                    } catch (IOException ex) {
                        Logger.getLogger(ModifEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
            
            }
        });
        
        
    }

   

}
