/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudUser;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

    public static User loggduser = new User();
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Hyperlink reg;

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
    @FXML
    public void Authentification(ActionEvent event) {
        if(tfusername.getText().isEmpty() || tfpassword.getText().isEmpty() )
        {
        JOptionPane.showMessageDialog(null, "un ou plusieurs champs necessaires sont vides !");
        }
        else{
        CrudUser us = new CrudUser();
        User usr = new User();
        usr.setUsername(tfusername.getText());
        usr.setPassword(tfpassword.getText());
        boolean verify = us.VerifyUser(usr);
        boolean verifyAd = us.VerifyIfAdmin(usr);
        int UserID = us.GetUserId(usr);
        if (verify && !verifyAd) {
            try {
                loggduser.setPassword(tfpassword.getText());
                loggduser.setUsername(usr.getUsername());
                loggduser.setId(UserID);
                loggduser.setRoles(us.GetUserRole(usr));
                loggduser.setNom(us.GetUsername(usr));
                loggduser.setPrenom(us.GetUserprenom(usr));
                loggduser.setEmail(us.GetUsermail(usr).getEmail());
                loggduser.setNum_tel(us.GetUserphone(usr));
                JOptionPane.showMessageDialog(null, "client " + usr.getUsername() +"  id:"+loggduser.getId()+ "role: "+loggduser.getRoles()+"!");
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
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/HomeAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
        }
}   } 
    @FXML
    public void Register(ActionEvent event) {
        
            
           try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/Register.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
        }
    @FXML
    public void forgotpass(ActionEvent event) {
        if(tfusername.getText().isEmpty() )
        {
        JOptionPane.showMessageDialog(null, "aucun username inserée !");
        }
        else{
        User usr=new User();
        CrudUser servu=new CrudUser();
        String mailtop="";
        usr.setUsername(tfusername.getText());
        mailtop=servu.GetUsermail(usr).getEmail();
        String mailcont=servu.GetUsermail(usr).getPassword();
        try{
            
            String host ="smtp.gmail.com" ;
            String user = "mohamedjihed.kammoun@esprit.tn";
            String pass = "furiothunder";
            String to = mailtop;
            String from = "mohamedjihed.kammoun@esprit.tn";
            String subject = "recuperation mot de passe";
            String messageText = "le mot de passe pour l'utilisateur "+usr.getUsername()+" est " +servu.GetUsermail(usr).getPassword();
            System.out.println(messageText);
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           JOptionPane.showMessageDialog(null, "un mail vous a été envoyé contenant votre mot de passe !");
        }catch(Exception ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "un probleme a été survenu lors de l'envoi du mail verifiez vos parametres de securité !");
        }
            
           
        }}
} 