/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;
import entites.Categorie;
import entites.Evenement;
import entites.Produit;
import static gui.controller.EventClientController.E;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.CrudCategorie;
import services.CrudEvenement;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class EventClientController implements Initializable {

 
 CrudEvenement ps = new CrudEvenement();
 public  static  Evenement E=new Evenement();
    Pagination page = new Pagination(round((ps.AfficherTT().size() / 5) + 1));
    
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
    private Button button1;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
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
          button1.setVisible(false);
            button2.setVisible(false);
              button3.setVisible(false);
                button4.setVisible(false);
                  button5.setVisible(false);
                    button6.setVisible(false);
        
        page.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                pageNumber.setText(Integer.toString(param));
                return new VBox();
            }
        
    });
       page.setTranslateX(100);
       page.setTranslateY(215);
        pane1.getChildren().add(page);
        pane2.getChildren().add(page);
       
              pane4.getChildren().add(page);
                       pane5.getChildren().add(page);
                            pane6.getChildren().add(page);
                             pane3.getChildren().add(page);
       // PageAnchor.getChildren().add(button1);
        
    
     

        pageNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Integer.parseInt(newValue) == 0) {
                    AfficherEvent(ps.AfficherTT(), 0);
                       
                }
                if (Integer.parseInt(newValue) == 1) {
                    AfficherEvent(ps.AfficherTT(), 6);
                }
                if (Integer.parseInt(newValue) == 2) {
                    AfficherEvent(ps.AfficherTT(), 12);
                }
                if (Integer.parseInt(newValue) == 3) {
                    AfficherEvent(ps.AfficherTT(), 18);
                }
                if (Integer.parseInt(newValue) == 4) {
                    AfficherEvent(ps.AfficherTT(), 24);
                }

            }

            
        });
    
    }
        
    
         @FXML
    private void Menu(MouseEvent event) {
    
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    public void AfficherEvent(List<Evenement> myList, int CurrentPage)  {
   String a="C:\\Users\\iheb ben fraj\\Desktop\\piJava\\pidesktop1.0\\src\\utils\\assets\\";
        try {
        
        if (myList.size() >= CurrentPage) {
         
                Image img1 = new Image(new FileInputStream(a+myList.get(CurrentPage).getNom_image()), 520, 300, false, false);
                System.out.println(myList.get(CurrentPage).getNom_image());
               
                image1.setImage(img1);
                label1.setText(myList.get(CurrentPage).getNom());
           button1.setVisible(true);
        } else {
            image1.setImage(null);
            label1.setText("");
             button1.setVisible(false);
           
        }
        if (myList.size() > CurrentPage + 1) {
            Image img2 = new Image(new FileInputStream(a+myList.get(CurrentPage + 1).getNom_image()), 520, 300, false, false);
            
              image2.setImage(img2);
            label2.setText(myList.get(CurrentPage + 1).getNom());
           button2.setVisible(true);
        } else {
            image2.setImage(null);
            label2.setText("");
             button2.setVisible(false);
        }
        if (myList.size() > CurrentPage + 2) {
            Image img3 = new Image(new FileInputStream( a+myList.get(CurrentPage + 2).getNom_image()), 520, 300, false, false);
            
            image3.setImage(img3);
            label3.setText(myList.get(CurrentPage + 2).getNom());
            button3.setVisible(true);
            
        } else {
            label3.setText("");
            image3.setImage(null);
            button3.setVisible(false);
            
        }
        if (myList.size() > CurrentPage + 3) {
              Image img4 = new Image(new FileInputStream(a+myList.get(CurrentPage + 3).getNom_image()), 520, 300, false, false);
            
            image4.setImage(img4);
            label4.setText(myList.get(CurrentPage + 3).getNom());
            button4.setVisible(true);
        } else {
            image4.setImage(null);
            label4.setText("");
             button4.setVisible(false);
        }
        if (myList.size() > CurrentPage + 4) {
              Image img5 = new Image(new FileInputStream(a+myList.get(CurrentPage + 4).getNom_image()), 520, 300, false, false);
            
            image5.setImage(img5);
            label5.setText(myList.get(CurrentPage + 4).getNom());
            button5.setVisible(true);
        } else {
            image5.setImage(null);
            label5.setText("");
            
             button5.setVisible(false);
            
        }
        if (myList.size() > CurrentPage + 5) {
              Image img6 = new Image(new FileInputStream( a+myList.get(CurrentPage + 5).getNom_image()), 520, 300, false, false);
            
            image6.setImage(img6);
            label6.setText(myList.get(CurrentPage + 5).getNom());
               button6.setVisible(true);
        } else {
            image6.setImage(null);
            label6.setText("");
             button6.setVisible(false);
        }

      } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clickButt1(ActionEvent event) {
     
        
       
     int i =page.getCurrentPageIndex()*6;
         
     
        E=ps.AfficherTT().get(i);
          this.goToDetail(event);
        
     
    }

    @FXML
    private void clickButt2(ActionEvent event) {
     
        
        
     int i =page.getCurrentPageIndex()*6+1;
         
     
        E=ps.AfficherTT().get(i);
        this.goToDetail(event);
        
    }

    @FXML
    private void clickButt3(ActionEvent event) {
         
        
      
     int i =page.getCurrentPageIndex()*6+2;
         
     
        E=ps.AfficherTT().get(i);
            
           this.goToDetail(event);
        
    }

    @FXML
    private void clickButt4(ActionEvent event) {
         
        
       
     int i =page.getCurrentPageIndex()*6+3;
         
     
        E=ps.AfficherTT().get(i);
            
         this.goToDetail(event);
        
    }

    @FXML
    private void clickButt5(ActionEvent event) {
         
        
     int i =page.getCurrentPageIndex()*6+4;
         
     
        E=ps.AfficherTT().get(i);
            
        this.goToDetail(event);
    }

    @FXML
    private void clickButt6(ActionEvent event)  {
         
        
    
     int i =page.getCurrentPageIndex()*6+5;
         
     
        E=ps.AfficherTT().get(i);
            
    this.goToDetail(event);
           
    }
    
    public void goToDetail(ActionEvent event){
     try {
         Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/DetailEvent.fxml"));
         Scene home_page_scene = new Scene(home_page_parent);
         Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         
         //       app_stage.hide(); //optional
         app_stage.setScene(home_page_scene);
         app_stage.show();
     } catch (IOException ex) {
         Logger.getLogger(EventClientController.class.getName()).log(Level.SEVERE, null, ex);
     }
       }
}
