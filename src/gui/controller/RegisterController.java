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
    @FXML
    private TextField remail1;
    @FXML
    private TextField rname1;
    @FXML
    private TextField rlastname2;

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
        us.setUsername_canonical(rusername.getText());
        us.setEmail(remail1.getText());
        us.setEmail_canonical(remail1.getText());
        us.setEnabled(1);
        us.setNom(rname1.getText());
        us.setPrenom(rlastname2.getText());

        String role=(String) roleCombo.getValue();
        switch(role){
         case("Client"):us.setRoles("a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
         break;
         case("Admin"):us.setRoles("a:1:{i:0;s:19:\"ROLE_ADMINISTRATEUR\";}");
         break;
         case("Association"):us.setRoles("a:1:{i:0;s:16:\"ROLE_ASSOCIATION\";}");
         break;
         case("Magasin"):us.setRoles("a:1:{i:0;s:12:\"ROLE_MAGASIN\";}");
         break;
         case("Livreur"):us.setRoles("a:1:{i:0;s:12:\"ROLE_LIVREUR\";}");
         break;
         case("Dresseur"):us.setRoles("a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}");
         break;
         case("Veterinaire"):us.setRoles("a:1:{i:0;s:16:\"ROLE_VETERINAIRE\";}");
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
