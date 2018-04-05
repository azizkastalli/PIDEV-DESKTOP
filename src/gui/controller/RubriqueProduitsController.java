/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Vote;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import static gui.controller.StoreController.P;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.CrudVote;
import static gui.controller.LoginController.loggduser;
/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class RubriqueProduitsController implements Initializable {

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
    private ImageView imageprod;
    @FXML
    private Label nomprod;
    @FXML
    private Text description;
    @FXML
    private Label caracteristique;
    @FXML
    private Label prix;
    @FXML
    private Label quantite;
    @FXML
    private Label msg;
    @FXML
    private Rating rating;
    @FXML
    private Label vote;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CrudVote V = new CrudVote();
        Vote Vo = new Vote();
        if(V.VerifyIfUser(loggduser.getId())==true && V.VerifyIfprod(P.getLabel())==true)
        {
            rating.setVisible(false);
            String some=Float.toString(V.Somme(P.getLabel()));
            vote.setText("Rating :"+some);
        
       
        try {
            rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                
                Vo.setId_produit(P.getLabel());
                Vo.setVote(t1);
                Vo.setId_user(loggduser.getId());
                try {
                    V.Create(Vo);
                } catch (SQLException ex) {
                    Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               }     
        });
            Image img = new Image(new FileInputStream(P.getNom_image()),301,345,false,false);
            imageprod.setImage(img);
            nomprod.setText("Produit: " +P.getLabel());
            description.setText("Description: "+P.getDescription());
            caracteristique.setText("caracteristiques: "+P.getCaracteristiques());
            prix.setText(String.valueOf(P.getPrix_nouv()) + " DT");
            quantite.setText(String.valueOf(P.getQuantite()) +" Piece");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
        String some=Float.toString(V.Somme(P.getLabel()));
        vote.setText("Rating :"+some);
        rating.setVisible(true);
        System.out.println(V.Somme(P.getLabel()));
        try {
            rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                
                Vo.setId_produit(P.getLabel());
                Vo.setVote(t1);
                Vo.setId_user(loggduser.getId());
                try {
                    V.Create(Vo);
                } catch (SQLException ex) {
                    Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               }     
        });
            Image img = new Image(new FileInputStream(P.getNom_image()),301,345,false,false);
            imageprod.setImage(img);
            nomprod.setText("Produit: " +P.getLabel());
            description.setText("Description: "+P.getDescription());
            caracteristique.setText("caracteristiques: "+P.getCaracteristiques());
            prix.setText(String.valueOf(P.getPrix_nouv()) + " DT");
            quantite.setText(String.valueOf(P.getQuantite()) +" Piece");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }    

    @FXML
    private void Menu(MouseEvent event) {
        
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }

    @FXML
    private void comment(ActionEvent event) {
        Stage stage=new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Commentaire.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
