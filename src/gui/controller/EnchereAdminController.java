/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Produit;
import entites.Session;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.CrudEncheres;
import services.CrudSession;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class EnchereAdminController implements Initializable {

    @FXML
    private HBox ev;
    @FXML
    private TableColumn<Session, Integer> id;
    @FXML
    private TableColumn<Session, Double> mise;
    @FXML
    private TableColumn<Session, String> gagnant;
    @FXML
    private TableColumn<Session, String> etat;
    @FXML
    private TableView<Session> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          CrudSession session =new CrudSession();
        ArrayList arrayList=null;
        try {
             arrayList = (ArrayList) session.SelectAll();
        } catch (SQLException ex) {}
        
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        
        id.setCellValueFactory(new PropertyValueFactory<Session,Integer>("id"));
        mise.setCellValueFactory(new PropertyValueFactory<Session,Double>("derniere_mise"));
        gagnant.setCellValueFactory(new PropertyValueFactory<Session,String>("id_gagnant"));
        etat.setCellValueFactory(new PropertyValueFactory<Session,String>("etat"));
        
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
    private void HistoriqueSession(MouseEvent event)
    {
        
         Session S = table.getSelectionModel().getSelectedItem();        
         
    }
    
}
