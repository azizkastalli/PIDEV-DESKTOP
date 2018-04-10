/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.User;
import static gui.controller.LoginController.loggduser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
    @FXML
    private TextField rphone;

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
        if(rusername.getText().isEmpty() || rpassword.getText().isEmpty() ||remail1.getText().isEmpty() ||rname1.getText().isEmpty()||rlastname2.getText().isEmpty())
        {
        JOptionPane.showMessageDialog(null, "un ou plusieurs champs necessaires sont vides !");
        }
        else{
        us.setUsername(rusername.getText());
        us.setPassword(rpassword.getText());
        us.setUsername_canonical(rusername.getText());
        us.setEmail(remail1.getText());
        us.setEmail_canonical(remail1.getText());
        us.setEnabled(1);
        us.setNom(rname1.getText());
        us.setPrenom(rlastname2.getText());
        us.setNum_tel(rphone.getText());

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
             JOptionPane.showMessageDialog(null, "compte crée avec succées!");
        } 
         catch (SQLException ex) {
             System.out.println("erreur");
             JOptionPane.showMessageDialog(null, "username ou email deja utilisée!");
        }
       }}
    @FXML
    public void login(ActionEvent event) {
        
            
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