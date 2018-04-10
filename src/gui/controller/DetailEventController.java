/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;


import entites.Participation;
import java.util.Date;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import static gui.controller.EventClientController.E;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import services.ServiceReservation;
import static gui.controller.LoginController.loggduser;
import java.sql.SQLException;
import javafx.scene.control.Button;
import services.CrudEvenement;
import static gui.controller.EventClientController.j;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.Alert;


/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class DetailEventController implements Initializable {

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
    private ImageView image1;
    @FXML
    private Label titre;
    @FXML
    private Text tfDescription;
    @FXML
    private Label nbre;
    @FXML
    private Label dateD;
    @FXML
    private Label dateF;
    @FXML
    private Button butt;
    @FXML
    private Button fbButt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
             String a="C:\\Users\\USER\\Documents\\PIDEV-DESKTOP\\src\\utils\\assets\\";
            Image img6 = new Image(new FileInputStream( a+E.getNom_image()),393,394, false, false);
         
            image1.setImage(img6);
            tfDescription.setText("Description : "+E.getDescription());
            nbre.setText("Nombre participants restant est :"+E.getNbr_participants());
            dateD.setText("Date Debut :"+E.getDate_debut());
            dateF.setText("Date Fin : "+E.getDate_fin());
            System.out.println(E.getId()+E.getNom());
            System.out.println(E.getDate_debut());
            titre.setText(E.getNom());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           ServiceReservation SR=new ServiceReservation();
              Timestamp dateSys = new Timestamp(System.currentTimeMillis());
        if(SR.VerifParticipation(E.getId(),loggduser.getId())==true&&E.getNbr_participants()>0&&E.getDate_debut().after(dateSys))
        {
        butt.setText("annuler");
        
        } else if(SR.VerifParticipation(E.getId(),loggduser.getId())==false&&E.getNbr_participants()>0&&E.getDate_debut().after(dateSys)){
        butt.setText("reserver");
        }
        else if(E.getDate_debut().before(dateSys))
        {
         butt.setVisible(false);
          new Alert(Alert.AlertType.ERROR, "date deja evenement expriré").show();
         
       
        }
        else{
             butt.setText("evenement expiré");
        }
        
        // your date

    }    

       @FXML
    private void Menu(MouseEvent event) {
    
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }

    @FXML
    private void ReservAnnule(ActionEvent event) {
          ServiceReservation SR= new ServiceReservation();
        
        if(butt.getText().equals("reserver"))
        {   
            try {
            int id= loggduser.getId();
            int id_ev=E.getId();
            Participation P = new Participation(id, id_ev);
            
            SR.Create(P);
            butt.setText("annuler");
                CrudEvenement E1=new CrudEvenement();
                E1.decrementNbrParticipant(E.getId());
             int  nb=E1.SelectAll().get(j).getNbr_participants();
             nbre.setText("Nombre participants restant est :"+nb);
            } catch (SQLException ex) {
                Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(butt.getText().equals("annuler"))
        {
            try {
                int id= loggduser.getId();
            int id_ev=E.getId();
            Participation P = new Participation(id, id_ev);
            
            SR.Delete(P);
            butt.setText("reserver");
               CrudEvenement E1=new CrudEvenement();
                E1.incrementNbrParticipant(E.getId());
               int  nb=E1.SelectAll().get(j).getNbr_participants();
               
             nbre.setText("Nombre participants restant est :"+nb);
            } catch (SQLException ex) {
                Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else{
         new Alert(Alert.AlertType.ERROR, "evenement expriré").show();
        }
    }

    @FXML
    private void FBClick(ActionEvent event) throws FileNotFoundException {
          String accessToken="EAACEdEose0cBAGhqlqSr5mOvdDWcNFKljGEBBiwKWb52KSYJRZCrMnGnVmO4Xxlab4PFfnpVY94lxg79fy6Tizlt4MxBM9SNhMkSZBNZCJXKtZALO5WPEBgAUw3KJECiSR11QjVsUlHrFamvZBktvkSwQEDvks3yS7pkYi9ErqWTVDDM4ZBMiBCEeIjhlGISvyZAiRhj0xbGgZDZD";
        FacebookClient fbClient=new DefaultFacebookClient(accessToken);
        FileInputStream fis=new FileInputStream(new File("C:\\Users\\USER\\Documents\\PIDEV-DESKTOP\\src\\utils\\assets\\"+E.getNom_image()));    
        FacebookType response =fbClient.publish("me/photos",FacebookType.class,BinaryAttachment.with(E.getNom_image(),fis), Parameter.with("message",E.getDescription()));
        System.out.println("fb.com/"+response.getId());
           new Alert(Alert.AlertType.INFORMATION, "evenement partagé sur facebook avec succés").show();
        
        
        
        
    }
}
