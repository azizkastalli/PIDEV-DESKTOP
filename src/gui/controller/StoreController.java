/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class StoreController implements Initializable {

   public static Produit P = new Produit();
    CrudProduit ps = new CrudProduit();
    Pagination page = new Pagination(round((ps.filtreEtat().size() / 5) + 1));
    
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
    private Hyperlink link;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane6;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    
  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        page.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                pageNumber.setText(Integer.toString(param));
                return new VBox();
            }
        
    });
        
     page.setTranslateX(-260);
     page.setTranslateY(215);
        pane1.getChildren().add(page);
        pane2.getChildren().add(page);
        pane3.getChildren().add(page);
        pane4.getChildren().add(page);
        pane5.getChildren().add(page);
        pane6.getChildren().add(page);
        
        
        
        pageNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
 
 
                if (Integer.parseInt(newValue) == 0) {
                    AfficherPrroduits(ps.filtreEtat(), 0);
               
                    
                }
                if (Integer.parseInt(newValue) == 1) {
                    AfficherPrroduits(ps.filtreEtat(), 6);
                    
                }
                if (Integer.parseInt(newValue) == 2) {
                    AfficherPrroduits(ps.filtreEtat(), 12);
                     
                }
                if (Integer.parseInt(newValue) == 3) {
                    AfficherPrroduits(ps.filtreEtat(), 18);
 
                }
                if (Integer.parseInt(newValue) == 4) {
                    AfficherPrroduits(ps.filtreEtat(), 24);
 
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
                button1.setVisible(true);
            } else {
                image1.setImage(null);
                label1.setText("");
                label7.setText("");
                button1.setVisible(false);
            }
            if (myList.size() > CurrentPage + 1) {
                Image img2 = new Image(new FileInputStream(myList.get(CurrentPage + 1).getNom_image()), 520, 300, false, false);
               String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image2.setImage(img2);
                label2.setText(myList.get(CurrentPage + 1).getLabel());
                label8.setText(prix);
                button2.setVisible(true);
            } else {
                image2.setImage(null);
                label2.setText("");
                label8.setText("");
                button2.setVisible(false);
            }

            if (myList.size() > CurrentPage + 2) {
                Image img3 = new Image(new FileInputStream( myList.get(CurrentPage + 2).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image3.setImage(img3);
                label3.setText(myList.get(CurrentPage + 2).getLabel());
                label9.setText(prix);
                button3.setVisible(true);
            } else {
                label3.setText("");
                image3.setImage(null);
                label9.setText("");
                button3.setVisible(false);
            }
            if (myList.size() > CurrentPage + 3) {
                Image img4 = new Image(new FileInputStream(myList.get(CurrentPage + 3).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image4.setImage(img4);
                label4.setText(myList.get(CurrentPage + 3).getLabel());
                label10.setText(prix);
                button4.setVisible(true);
            } else {
                image4.setImage(null);
                label4.setText("");
                label10.setText("");
                button4.setVisible(false);
            }

            if (myList.size() > CurrentPage + 4) {
                Image img5 = new Image(new FileInputStream(myList.get(CurrentPage + 4).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image5.setImage(img5);
                label5.setText(myList.get(CurrentPage + 4).getLabel());
                label11.setText(prix);
                button5.setVisible(true);
            } else {
                image5.setImage(null);
                label5.setText("");
                label11.setText("");
                button5.setVisible(false);
            }

            if (myList.size() > CurrentPage + 5) {
                Image img6 = new Image(new FileInputStream( myList.get(CurrentPage + 5).getNom_image()), 520, 300, false, false);
                String prix = String.valueOf(myList.get(CurrentPage).getPrix_nouv());
                image6.setImage(img6);
                label6.setText(myList.get(CurrentPage + 5).getLabel());
                label12.setText(prix);
                button6.setVisible(true);
            } else {
                image6.setImage(null);
                label6.setText("");
                label12.setText("");
                button6.setVisible(false);
            }

        } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
        }

    }

  
    @FXML
    private void detail5(ActionEvent event) {
        int i =page.getCurrentPageIndex()*6+4;
        P=ps.SelectAll().get(i);
         this.GoToDetail(event);
    }

    @FXML
    private void detail6(ActionEvent event) {
        int i =page.getCurrentPageIndex()*6+5;
        P=ps.SelectAll().get(i);
         this.GoToDetail(event);
    }

    @FXML
    private void detail1(ActionEvent event) {
    
           int i =page.getCurrentPageIndex()*6;
        P=ps.SelectAll().get(i);
         this.GoToDetail(event);
    }

    @FXML
    private void detail3(ActionEvent event) {
        int i =page.getCurrentPageIndex()*6+2;
        P=ps.SelectAll().get(i);
         this.GoToDetail(event);
        
    }

    @FXML
    private void detail2(ActionEvent event) {
        int i =page.getCurrentPageIndex()*6+1;
        P=ps.SelectAll().get(i);
         this.GoToDetail(event);
        
    }

    @FXML
    private void detail4(ActionEvent event) {
        int i =page.getCurrentPageIndex()*6+3;
        P=ps.SelectAll().get(i);
         this.GoToDetail(event);
        
    }
    
    public void GoToDetail(ActionEvent event)
    {
     try {
           int i =page.getCurrentPageIndex()*6;
        P=ps.SelectAll().get(i);
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/RubriqueProduits.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

}
    
        
      
     
   
    

