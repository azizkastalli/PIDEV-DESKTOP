/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.CrudUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField rusername;
    @FXML
    private PasswordField rpassword;
    @FXML
    private ComboBox<?> roleCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    public void Registration(ActionEvent event) {
        
        System.out.println(roleCombo.getValue());
        CrudUser CU = new CrudUser();
        User us = new User();
        us.setUsername(rusername.getText());
        us.setPassword(rpassword.getText());
        String role=(String) roleCombo.getValue();
        switch(role){
         case("Client"):us.setRoles("a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
         break;
         case("Admin"):us.setRoles("a:1:{i:0;s:19:\"ROLE_ADMINISTRATEUR\";}");
         break;
        }
        
        
        
        
        
        
         try {
            CU.Create(us);
             System.out.println("utilisateur ajouté");
        } 
         catch (SQLException ex) {
             System.out.println("erreur");
        }
       }
    
}
