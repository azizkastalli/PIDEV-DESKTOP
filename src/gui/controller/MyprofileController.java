/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import static gui.controller.LoginController.loggduser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class MyprofileController implements Initializable {

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
    private ImageView image;
    @FXML
    private Label nameln;
    @FXML
    private Label profilemail;
    @FXML
    private Label profilusername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameln.setText(loggduser.getPrenom()+" "+loggduser.getNom());
        profilemail.setText(loggduser.getEmail());
        profilusername.setText(loggduser.getUsername());
        
        // TODO
    }    
@FXML
    public void modifierpass(ActionEvent event) {
        
            
           try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/modifierpass.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
        }
    @FXML
    private void Menu(MouseEvent event) {
        
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    @FXML
    public void logout(MouseEvent event) {
        
            
           try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
                                 }
    
                   }
    
}
