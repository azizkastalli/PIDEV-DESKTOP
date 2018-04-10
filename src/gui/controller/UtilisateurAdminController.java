/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Produit;
import entites.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudProduit;
import services.CrudUser;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class UtilisateurAdminController implements Initializable {
 
    public static User usr = new User();
    
    @FXML
    private HBox ev;
    @FXML
    private TableView<User> tableA;
    private TableColumn<Produit, String> nomproduit;
    private TableColumn<Produit, String> caracteristique;
    private TableColumn<Produit, Double> prixnouv;
    private TableColumn<Produit, Double> prixanc;
    private TableColumn<Produit, String> etat;
    private TableColumn<Produit, String> description;
    private ObservableList <User> data;
    @FXML
    private TableColumn<User,Integer> iduser;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> role;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              //Connection conn=dc.getConnection();
        CrudUser myTool = new CrudUser();
        User u = new User();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.SelectAll().size();i++)
        {
            u=(User) myTool.SelectAll().get(i);
            data.add(u);
        }
        iduser.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        tableA.setItems(data);
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
          
            
            //    app_stage.hide(); //optional
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
          
            
            //    app_stage.hide(); //optional
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
          
            
               // app_stage.hide(); //optional
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
          
            
              //  app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    
}