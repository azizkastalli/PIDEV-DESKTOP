/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.CrudUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfusername.setPromptText("Username ...");
        tfusername.setFocusTraversable(false);
        tfpassword.setPromptText("Password ...");
        tfpassword.setFocusTraversable(false);
}
    public void Authentification(ActionEvent event) {
        CrudUser us = new CrudUser();
        User usr = new User();
        usr.setUsername(tfusername.getText());
        usr.setPassword(tfpassword.getText());
        boolean verify = us.VerifyUser(usr);
        boolean verifyAd = us.VerifyIfAdmin(usr);
        int UserID = us.GetUserId(usr);
        if (verify && !verifyAd) {
            try {
                JOptionPane.showMessageDialog(null, "client " + usr.getUsername() + "!");
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/acceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
            
        } else if (verify && verifyAd) {
            
            try {
             JOptionPane.showMessageDialog(null, "admin " + usr.getUsername() + "!");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
//                Preferences pref = Preferences.userNodeForPackage(User.class);
//                pref.put("User_id",String.valueOf(UserID));
                Parent root = loader.load();
                tfusername.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}    
    
}