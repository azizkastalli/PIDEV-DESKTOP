/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.CrudUser;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class VeterinaireAdminController implements Initializable {

    @FXML
    private HBox ev;
    @FXML
    private TableView<User> tbl_vet;
    @FXML
    private TableColumn<User, String> col_id;
    @FXML
    private TableColumn<User, String> col_nom;
    @FXML
    private TableColumn<User, String> col_prenom;
    @FXML
    private TableColumn<User, String> col_tel;
    @FXML
    private Text txt_nom;
    @FXML
    private Text txt_prenom;
    @FXML
    private Text txt_mail;
    @FXML
    private Text txt_tel;
    @FXML
    private Text txt_adress;
    @FXML
    private Text txt_prix;
    @FXML
    private TextField edt_search;
    
    private List<User> lst_vet;
    private List<User> lst_vet_search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initData();
    }    

  @FXML
    private void ClickStore(MouseEvent event) {
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/StoreAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickEvenement(MouseEvent event) {
        
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/EvenementAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickEncheres(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/EnchereAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickServices(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickVeterinaire(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/VeterinaireAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickProduit(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ProduitAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

        @FXML
    private void HomeClick(ActionEvent event) {
              try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/HomeAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
   
    
    private void initData(){
        CrudUser serv = new CrudUser();
        lst_vet = new ArrayList<>();
        lst_vet_search = new ArrayList<>();
        try {
            lst_vet = serv.SelectAllVet();
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<User, String>("num_tel"));
        
        if(lst_vet.size() > 0){
            tbl_vet.setItems(FXCollections.observableArrayList(lst_vet));
            
            edt_search.textProperty().addListener((observable, oldValue, newValue) -> {
                SearchVet(newValue.toUpperCase());
            });
            
            tbl_vet.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("id : "+tbl_vet.getSelectionModel().getSelectedItem().getId());
                txt_nom.setText(tbl_vet.getSelectionModel().getSelectedItem().getNom());
                txt_prenom.setText(tbl_vet.getSelectionModel().getSelectedItem().getPrenom());
                txt_mail.setText(tbl_vet.getSelectionModel().getSelectedItem().getEmail());
                txt_adress.setText(tbl_vet.getSelectionModel().getSelectedItem().getAddress());
                txt_tel.setText(tbl_vet.getSelectionModel().getSelectedItem().getNum_tel());
                txt_prix.setText(String.valueOf(tbl_vet.getSelectionModel().getSelectedItem().getPrix_unitaire()));
            }});
        }
    }
    
    private void SearchVet(String s ){
     
     lst_vet_search = new ArrayList<>();
     for (User u : lst_vet) {
      if(String.valueOf(u.getId()).toUpperCase().contains(s) || u.getNom().toUpperCase().contains(s) || u.getPrenom().toUpperCase().contains(s) || u.getEmail().toUpperCase().contains(s) || u.getNum_tel().toUpperCase().contains(s)|| u.getAddress().toUpperCase().contains(s)){
             lst_vet_search.add(u);
       } 
     }
     tbl_vet.getItems().clear();
     tbl_vet.setItems(FXCollections.observableArrayList(lst_vet_search));
    
    }
    
}
