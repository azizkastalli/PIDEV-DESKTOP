/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.User;
import static gui.controller.LoginController.loggduser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.SQLException;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudUser;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class ModifierpassController implements Initializable {

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
    private PasswordField oldpass;
    @FXML
    private Button saveb;
    @FXML
    private PasswordField newpass1;
    @FXML
    private PasswordField newpass2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void Menu(MouseEvent event) {
        
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    @FXML
    public void savenpass(ActionEvent event) throws SQLException {
        
           if(loggduser.getPassword().equals(oldpass.getText())){ 
        if(newpass1.getText().equals(newpass2.getText())){
        CrudUser us = new CrudUser();
        User usr = new User();
        usr.setPassword(newpass1.getText());
        usr.setUsername(loggduser.getUsername());
        us.Updatepass(usr);
        
        JOptionPane.showMessageDialog(null, "mot de passe changé avec succes"+"!");
        
           try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/myprofile.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }}
        else{
        JOptionPane.showMessageDialog(null, "mot de passe reentré erronée"+"!"); 
        }
           }
           else{
       JOptionPane.showMessageDialog(null, "ancien mot de passe erronée"+"!");    
           }
        }
    
}
