/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import static gui.controller.LoginController.loggduser;
import entites.Commentaire;
import static gui.controller.StoreController.P;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.CrudCommentaire;
import utils.Dbconnection;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommentaireController implements Initializable {

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
    private Text nomprod;
    @FXML
    private TextField fild;
    @FXML
    private Button comm;
    @FXML
    private ImageView imgprod;
    private ObservableList <Commentaire> data;
    @FXML
    private TableColumn<Commentaire, String> user;
    @FXML
    private TableColumn<Commentaire, String> comment;
    @FXML
    private TableColumn<Commentaire, Date> dat;
    @FXML
    private TableView<Commentaire> tableC;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                CrudCommentaire myTool = new CrudCommentaire();
        Commentaire c = new Commentaire();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.Selectcom(P.getLabel()).size();i++)
        {
            c=(Commentaire) myTool.Selectcom(P.getLabel()).get(i);
            data.add(c);
        }
        user.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        comment.setCellValueFactory(new PropertyValueFactory<>("texte"));
        dat.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableC.setItems(data); 
        try {
               
                Image img = new Image(new FileInputStream(P.getNom_image()),301,345,false,false);
                imgprod.setImage(img);
                nomprod.setText("Produit: " +P.getLabel());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       
            
    }    

    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }

    @FXML
    private void ajout(ActionEvent event) {
             
        try {
            if(fild.getText().equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ecrit ton commentaire");
            }
            else
            {
            CrudCommentaire CC = new CrudCommentaire();
            Commentaire c = new Commentaire();
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            c.setDate(date);
            c.setTexte(fild.getText());
            c.setId_cible(P.getLabel());
            c.setId_client(loggduser.getUsername());
            System.out.println(c.getId_cible());
            System.out.println(c.getDate());
            System.out.println(c.getTexte());
            CC.Create(c);
            
            System.out.println("commentaire ajouté");
            }
            } catch (SQLException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
