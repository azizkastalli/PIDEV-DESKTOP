/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.AnimalSdf;
import static gui.controller.LoginController.loggduser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AnimalAdopteController implements Initializable {

    @FXML
    private Label acceuil1;
    @FXML
    private Label services1;
    @FXML
    private Label produits1;
    @FXML
    private Label veterinaires1;
    @FXML
    private TableView<AnimalSdf> tableA;
    @FXML
    private TableColumn<AnimalSdf, Integer> id;
    @FXML
    private TableColumn<AnimalSdf, Date> date;
    @FXML
    private TableColumn<AnimalSdf, String> lieu;
    @FXML
    private TableColumn<AnimalSdf, String> sexe;
    @FXML
    private TableColumn<AnimalSdf, ImageView> img;
    private ObservableList <AnimalSdf> data;
    CrudAnimalsdf myTool = new CrudAnimalsdf();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         AnimalSdf p = new AnimalSdf();
         
        data= FXCollections.observableArrayList();
        try {
            for(int i=0;i<myTool.SelectAll().size();i++)
            {
                p=(AnimalSdf) myTool.SelectAll().get(i);
                if (p.getEtat()==2 ){
                data.add(p);
            }}
        } catch (SQLException ex) {
            Logger.getLogger(AnimalsdfAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_trouvaille"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_trouvaille"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        img.setCellValueFactory(new PropertyValueFactory<>("IV"));
        
        tableA.setItems(null);
        tableA.setItems(data);
    }    
    

    @FXML
    private void Menu(MouseEvent event) {
        String dest=""; 
        String destination=""; 
        String type = event.getSource().getClass().getName();
        
        if(type.equals("javafx.scene.control.Label"))
          {
          Label ev = (Label) event.getSource();
          dest=ev.getId();
          }
         else if(type.equals("javafx.scene.image.ImageView"))
         {
         ImageView ev = (ImageView) event.getSource();
         dest=ev.getId();
         }
        
         switch (dest) {
            case "services1":
            
            switch (loggduser.getRoles()){
             case"a:1:{i:0;s:11:\"ROLE_CLIENT\";}":
                destination="RubriqueServices.fxml";
                break;
                
             case"a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}" :
                 destination="ListeRdv.fxml";
                break;}
            case "acceuil":
                destination="acceuil.fxml";
                break;
         }
    
            
                
            
         
         if(destination!="")
        {
 
        
         Parent home_page_parent = null;
            try {
                home_page_parent = FXMLLoader.load(getClass().getResource("/gui/"+destination));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
       
        }
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }

    @FXML
    private void Try(MouseEvent event) {
    }
    
}
