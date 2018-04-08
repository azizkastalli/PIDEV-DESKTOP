/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Categorie;
import entites.Evenement;
import static gui.controller.EventClientController.E;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CrudCategorie;
import services.CrudEvenement;
import static gui.controller.LoginController.loggduser;

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
public  static Evenement E=new Evenement();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               
        CrudEvenement CE=new CrudEvenement();
        int id=loggduser.getId();
        ArrayList<Evenement> arrayList=(ArrayList<Evenement>) CE.afficherSelonAssociation(id);
        
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TV.setItems(observableList);
    
        
        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("description"));
        nbrC.setCellValueFactory(new PropertyValueFactory<>("nbr_participants"));
        CatC.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("IV"));
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
    private void ClickRow(MouseEvent event) {
      
    }

    @FXML
    private void ModifierEvenement(ActionEvent event) throws IOException {
        
      
       E=TV.getSelectionModel().getSelectedItem();
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ModifierEvent.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
  
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) throws SQLException, IOException {
        
       String i=TV.getSelectionModel().getSelectedItem().getNom(); 
       Evenement E1=new Evenement();
       E1.setNom(i);
       CrudEvenement CE=new CrudEvenement();
       
       CE.Delete(E1);
       
       
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ModifSuppEvent.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
       
       
    }
}

