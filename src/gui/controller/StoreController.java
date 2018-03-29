/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.Math.round;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.CrudProduit;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class StoreController implements Initializable {

   
    CrudProduit ps = new CrudProduit();
    Pagination page = new Pagination(round((ps.SelectAll().size() / 5) + 1));
    
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

    private Pagination pagination;
    
    private AnchorPane AnchorPane;
    @FXML
    private ImageView image1;
    @FXML
    private Label label1;
    @FXML
    private ImageView image2;
    @FXML
    private Label label2;
    @FXML
    private ImageView image3;
    @FXML
    private Label label3;
    @FXML
    private ImageView image4;
    @FXML
    private Label label4;
    @FXML
    private ImageView image5;
    @FXML
    private Label label5;
    @FXML
    private ImageView image6;
    @FXML
    private Label label6;
    TextField pageNumber = new TextField();
     @FXML
    private AnchorPane PageAnchor;
    @FXML
    private Label label12;
    @FXML
    private Label label10;
    @FXML
    private Label label9;
    @FXML
    private Label label8;
    @FXML
    private Label label7;
    @FXML
    private Label label11;
  
    
  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        page.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                pageNumber.setText(Integer.toString(param));
                return new VBox();
            }
        
    });
        PageAnchor.setTopAnchor(page, 50.0);
        PageAnchor.setBottomAnchor(page, 10.0);
        PageAnchor.setLeftAnchor(page, 10.0);
        PageAnchor.setRightAnchor(page, 10.0);
        PageAnchor.getChildren().add(page);

        pageNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Integer.parseInt(newValue) == 0) {
                    AfficherPrroduits(ps.SelectAll(), 0);
                }
                if (Integer.parseInt(newValue) == 1) {
                    AfficherPrroduits(ps.SelectAll(), 6);
                }
                if (Integer.parseInt(newValue) == 2) {
                    AfficherPrroduits(ps.SelectAll(), 12);
                }
                if (Integer.parseInt(newValue) == 3) {
                    AfficherPrroduits(ps.SelectAll(), 18);
                }
                if (Integer.parseInt(newValue) == 2) {
                    AfficherPrroduits(ps.SelectAll(), 24);
                }

            }

            
        });
    
    }
        
    
         @FXML
    private void Menu(MouseEvent event) {
    
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    public void AfficherPrroduits(List<Produit> myList, int CurrentPage) {

        
        try {

            if (myList.size() >= CurrentPage) {
                Image img1 = new Image(new FileInputStream(myList.get(CurrentPage).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                
                image1.setImage(img1);
                label1.setText(myList.get(CurrentPage).getLabel());
                
                label7.setText(prix);
            } else {
                image1.setImage(null);
                label1.setText("");
                label7.setText("");
            }
            if (myList.size() > CurrentPage + 1) {
                Image img2 = new Image(new FileInputStream(myList.get(CurrentPage + 1).getNom_image()), 520, 300, false, false);
               String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image2.setImage(img2);
                label2.setText(myList.get(CurrentPage + 1).getLabel());
                label8.setText(prix);
            } else {
                image2.setImage(null);
                label2.setText("");
                label8.setText("");
            }

            if (myList.size() > CurrentPage + 2) {
                Image img3 = new Image(new FileInputStream( myList.get(CurrentPage + 2).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image3.setImage(img3);
                label3.setText(myList.get(CurrentPage + 2).getLabel());
                label9.setText(prix);
            } else {
                label3.setText("");
                image3.setImage(null);
                label9.setText("");
            }
            if (myList.size() > CurrentPage + 3) {
                Image img4 = new Image(new FileInputStream(myList.get(CurrentPage + 3).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image4.setImage(img4);
                label4.setText(myList.get(CurrentPage + 3).getLabel());
                label10.setText(prix);
            } else {
                image4.setImage(null);
                label4.setText("");
                label10.setText("");
            }

            if (myList.size() > CurrentPage + 4) {
                Image img5 = new Image(new FileInputStream(myList.get(CurrentPage + 4).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image5.setImage(img5);
                label5.setText(myList.get(CurrentPage + 4).getLabel());
                label11.setText(prix);
            } else {
                image5.setImage(null);
                label5.setText("");
                label11.setText("");
            }

            if (myList.size() > CurrentPage + 5) {
                Image img6 = new Image(new FileInputStream( myList.get(CurrentPage + 5).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image6.setImage(img6);
                label6.setText(myList.get(CurrentPage + 5).getLabel());
                label12.setText(prix);
            } else {
                image6.setImage(null);
                label6.setText("");
                label12.setText("");
            }

        } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
        }

    }
    } 
        
      
     
   
    

