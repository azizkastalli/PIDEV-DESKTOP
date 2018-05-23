/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.CrudCategorie;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class AfficherCategorieAdminController implements Initializable {

    @FXML
    private HBox ev;
    @FXML
    private TableView<Categorie> listeCategorie;
    @FXML
    private TableColumn<Categorie,Integer> id;
    @FXML
    private TableColumn<Categorie, String> nom;
    @FXML
    private TableColumn<Categorie, String> type;
    
      
   
    
    private TableColumn<Categorie,String> buttonCol;
    
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CrudCategorie CC=new CrudCategorie();
        ArrayList<Categorie> arrayList=(ArrayList<Categorie>) CC.SelectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listeCategorie.setItems(observableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        listeCategorie.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        
     
    
        
       
       
       
    }    
     @FXML
    private void ClickStore(MouseEvent event) {
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/StoreAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickEvenement(MouseEvent event) {
        
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/EvenementAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               // app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickEncheres(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/EnchereAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickServices(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
             //   app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickVeterinaire(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/VeterinaireAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickProduit(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ProduitAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void HomeClick(ActionEvent event) {
              try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/HomeAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               // app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void EditNom(CellEditEvent edittedCell) throws SQLException {
        
        CrudCategorie CC=new CrudCategorie();
            listeCategorie.setEditable(true);

    Categorie personSelected =  listeCategorie.getSelectionModel().getSelectedItem();
    
    personSelected.setNom(edittedCell.getNewValue().toString());
       
    
        int id =  listeCategorie.getSelectionModel().getSelectedItem().getId();
       
                String nom=listeCategorie.getSelectionModel().getSelectedItem().getNom();
                 String type=listeCategorie.getSelectionModel().getSelectedItem().getType();
                
           

        if(nom.isEmpty()){
                  new Alert(Alert.AlertType.INFORMATION, "champ nom  vide").show();
           }
           else {
                Categorie c=new Categorie(id, nom,type);
        CC.Update(c);
           new Alert(Alert.AlertType.INFORMATION, "Catégorie modifié").show();
        
        }
        
    
    
            }

    @FXML
    private void EditType(CellEditEvent edittedCell) throws SQLException {
        
           CrudCategorie CC=new CrudCategorie();
            listeCategorie.setEditable(true);

    Categorie personSelected =  listeCategorie.getSelectionModel().getSelectedItem();
    
    personSelected.setType(edittedCell.getNewValue().toString());
       
    
        int id =  listeCategorie.getSelectionModel().getSelectedItem().getId();
       
                String nom=listeCategorie.getSelectionModel().getSelectedItem().getNom();
                 String type=listeCategorie.getSelectionModel().getSelectedItem().getType();
                
             //   System.out.println(id+type+nom);

        
       
           if(type.isEmpty()){
                  new Alert(Alert.AlertType.INFORMATION, "champ type vide").show();
           }
           else {
                Categorie c=new Categorie(id, nom,type);
        CC.Update(c);
           new Alert(Alert.AlertType.INFORMATION, "Catégorie modifié").show();
        
        }
        
                     

        
    }
    
    
   
}
