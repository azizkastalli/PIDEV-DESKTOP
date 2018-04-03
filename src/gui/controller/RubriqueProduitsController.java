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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import services.CrudVote;

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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudVote V = new CrudVote();
        Vote Vo = new Vote();
        try {
            rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                System.out.println(t1);
                System.out.println(P.getLabel());
                Vo.setId_produit(P.getLabel());
                Vo.setVote(t1);
                try {
                    V.Create(Vo);
                } catch (SQLException ex) {
                    Logger.getLogger(RubriqueProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
              msg.setText("Rating : "+ t1.toString()); }     
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

    @FXML
    private void Menu(MouseEvent event) {
        
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    
}
