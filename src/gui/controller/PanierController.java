/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import com.jfoenix.controls.JFXButton;
import static gui.controller.LoginController.loggduser;
import entites.Commande;
import entites.Produit;
import entites.Lignecommande;
import static gui.controller.LoginController.loggduser;
import static gui.controller.StoreController.p;
import services.CrudCommande;
import services.CrudProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import static javax.management.Query.value;
import javax.swing.JOptionPane;
import services.CrudLignecommande;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class PanierController implements Initializable {

    List<Commande> lst_com;
    CrudCommande serviceCommande = new CrudCommande();
    CrudLignecommande servicelCommande = new CrudLignecommande();
    CrudProduit servP = new CrudProduit();
    Produit p;
    private TextField nbrArtcile;
    @FXML
    private ScrollPane anchorGridPane;
    private final GridPane GP = new GridPane();
    private int j=0;
    private int i=0;
    private int k=0;
    private int l=0;
    private int scroll=0;   
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
    private Label passercm;
    @FXML
    private Label lprixtot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
        
        
        

        //init
        ArrayList <Produit> lst_P = new ArrayList<>();
        ArrayList<Pane> ListePane = new ArrayList<>();
        double lprix=0;
         //setting up the GridPane
        GP.setPadding(new Insets(10,10,10,10));
        GP.setHgap(5);
        GP.setVgap(5);
        
        //select DATA from DB
        for (int i = 0; i < StoreController.panier.size(); i++) {
            lst_P.add(servP.findById((Integer) StoreController.panier.get(i).get(0)));
        }
        
        //put DATA
        for(Produit E : lst_P)
        {   int quant=(int) StoreController.panier.get(k).get(1);
            k++;
            lprix=lprix+quant*E.getPrix_nouv();
            //image set
            ImageView IV= new ImageView();
            Image I = new Image("/utils/assets/"+E.getNom_image());
            IV.setImage(I);
            IV.setFitWidth(230);
            IV.setFitHeight(177);
            IV.setLayoutX(6);;
            
            //pane set
            Pane P = new Pane();
            P.setId(Integer.toString(E.getId()));
            P.setPrefWidth(246);
            P.setPrefHeight(277);
            P.setStyle("-fx-background-color: #4f86dd");
            //label set 
            Label L= new Label(E.getLabel());
              L.setLayoutY(185);
              L.setLayoutX(70);
              L.setStyle("-fx-font-size:20");
              //L.setStyle("-fx-font-weight:bold");
              //L.setStyle("-fx-text-fill: black");
              L.setPrefHeight(25);
              L.setPrefWidth(246);
             Label L2= new Label(Integer.toString(quant));
             System.out.println(Integer.toString(quant));
              L2.setLayoutY(185);
              L2.setLayoutX(50);
              L2.setStyle("-fx-font-size:20");
              //L.setStyle("-fx-font-weight:bold");
              //L.setStyle("-fx-text-fill: black");
              L2.setPrefHeight(25);
              L2.setPrefWidth(246);
                          
           
                   
            P.getChildren().addAll(IV,L,L2);            
            ListePane.add(P);
            GP.add(P,j,i);            

            j++;
            if(j>2)
            {i++;
            j=0;}
           
           }
        
          anchorGridPane.setContent(GP);
          lprixtot.setText("Prix total: "+lprix+"DNT");
         
      
              

 
   
    
    }
    

    private void Tostore(ActionEvent event) throws ParseException, SQLException {
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/store.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }
    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);   
    }
    @FXML
    private void Passercommande(MouseEvent event) throws SQLException {
        Commande cmd=new Commande();
        Lignecommande lcmd=new Lignecommande();
        double prixtot=0;
         ArrayList <Produit> lst_P = new ArrayList<>();
         for (int i = 0; i < StoreController.panier.size(); i++) {
            lst_P.add(servP.findById((Integer) StoreController.panier.get(i).get(0)));
        }
         l=0;
         for(Produit E : lst_P)
        {   int quant=(int) StoreController.panier.get(l).get(1);
            l++;
            prixtot=prixtot+(quant*E.getPrix_nouv());
            
        }
         cmd.setEtat(0);
         cmd.setId_client(loggduser.getId());
         cmd.setPrix_tot(prixtot);
         serviceCommande.Create(cmd);
         l=0;
         for(Produit E : lst_P)
        {   int quant=(int) StoreController.panier.get(l).get(1);
            
            lcmd.setId_client(loggduser.getId());
            lcmd.setId_produit((int) StoreController.panier.get(l).get(0));
            lcmd.setQte(quant);
            lcmd.setId_commande(serviceCommande.findlast(loggduser.getId()));
            servicelCommande.Create(lcmd);
            l++;
        }
         StoreController.panier.clear();
         lst_P.clear();
         try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/panier.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
         JOptionPane.showMessageDialog(null, "commande passé avec succes"+"!");
         
         
         
    }
   @FXML
    private void ViderPanier(MouseEvent event) {
        StoreController.panier.clear();
         try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/panier.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }
    @FXML
    private void Mescommandes(MouseEvent event) {
         try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/Mescommandes.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }

}

// System.out.println(panier.listIterator());