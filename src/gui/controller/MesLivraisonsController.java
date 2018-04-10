/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Commande;
import entites.Livraison;
import static gui.controller.LoginController.loggduser;
import static gui.controller.MescommandesController.C;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudCommande;
import services.CrudLivraison;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MesLivraisonsController implements Initializable {

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
    private TableView<Livraison> tableA;
    @FXML
    private TableColumn<Livraison, Integer> ref;
    
    @FXML
    private TableColumn<Livraison, String> etat;
    @FXML
    private TableColumn<Livraison, Integer> client;
    private ObservableList <Livraison> data;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudLivraison myTool = new CrudLivraison();
        Livraison p = new Livraison();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.SelectAll().size();i++)
        { if(myTool.SelectAll().get(i).getId_livreur()==loggduser.getId())
        { p= myTool.SelectAll().get(i);
            data.add(p);}
        }
        ref.setCellValueFactory(new PropertyValueFactory<>("id"));
        client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat1"));
        tableA.setItems(data);
    }    
    @FXML
    private void Menu(MouseEvent event) {
        
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    } 
    @FXML
    private void Leslivraisons(MouseEvent event) {
         try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/Leslivraisons.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }
    @FXML
    private void terminerliv(MouseEvent event) throws IOException, SQLException {
        CrudCommande myTool = new CrudCommande();
        CrudLivraison myTool1 = new CrudLivraison();
        Livraison L = new Livraison();
        if (tableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            L=tableA.getSelectionModel().getSelectedItem();
            L.setEtat(1);
            myTool1.Update(L);
            C.setId(L.getId_commande());
            C=myTool.Select(C);
            C.setEtat(2);
            myTool.Update(C);
                System.out.println(C.getId());
            //id.setText(String.valueOf(p.getId()));
           
            
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/MesLivraisons.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une livraison");
            }      
    }
    
}
