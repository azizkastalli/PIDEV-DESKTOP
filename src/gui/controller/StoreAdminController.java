/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Commande;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudCommande;
import services.CrudProduit;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class StoreAdminController implements Initializable {
 
    public static Commande C = new Commande();
    
    @FXML
    private HBox ev;
    @FXML
    private TableView<Commande> tableA;
    
    private ObservableList <Commande> data;
    @FXML
    private Button modifier;
    @FXML
    private TableColumn<Commande, Integer> ref;
    @FXML
    private TableColumn<Commande, Integer> client;
    @FXML
    private TableColumn<Commande, Double> prixntot;
    @FXML
    private TableColumn<Commande, String> etat;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              //Connection conn=dc.getConnection();
        CrudCommande myTool = new CrudCommande();
        Commande p = new Commande();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.SelectAll().size();i++)
        {
            p=(Commande) myTool.SelectAll().get(i);
            data.add(p);
        }
        ref.setCellValueFactory(new PropertyValueFactory<>("id"));
        client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        prixntot.setCellValueFactory(new PropertyValueFactory<>("prix_tot"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat1"));
        tableA.setItems(data);
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
          
            
               // app_stage.hide(); //optional
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
          
            
            //    app_stage.hide(); //optional
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
          
            
            //    app_stage.hide(); //optional
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
          
            
               // app_stage.hide(); //optional
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
          
            
              //  app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
         if (tableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            C=tableA.getSelectionModel().getSelectedItem();
            
                System.out.println(C.getId());
            //id.setText(String.valueOf(p.getId()));
           
            
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/LigneCommandeadmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une commande");
            }
        
    }
}