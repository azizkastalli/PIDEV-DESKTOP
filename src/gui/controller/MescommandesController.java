/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Commande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudCommande;
import static gui.controller.LoginController.loggduser;
import static gui.controller.StoreAdminController.C;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class MescommandesController implements Initializable {

    public static Commande C = new Commande();
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
    private TableView<Commande> tableA;
    
    private ObservableList <Commande> data;
    
    @FXML
    private TableColumn<Commande, Integer> ref;
    @FXML
    private TableColumn<Commande, Integer> client;
    @FXML
    private TableColumn<Commande, Double> prixntot;
    @FXML
    private TableColumn<Commande, Integer> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudCommande myTool = new CrudCommande();
        Commande p = new Commande();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.SelectAllclient(loggduser.getId()).size();i++)
        {
            p=(Commande) myTool.SelectAllclient(loggduser.getId()).get(i);
            data.add(p);
        }
        ref.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixntot.setCellValueFactory(new PropertyValueFactory<>("prix_tot"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableA.setItems(data);
    }    

    @FXML
    private void Menu(MouseEvent event) {
        
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    @FXML
    private void modifier(MouseEvent event) throws IOException {
         if (tableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            C=tableA.getSelectionModel().getSelectedItem();
            
                System.out.println(C.getId());
            //id.setText(String.valueOf(p.getId()));
           
            
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Macommande.fxml"));
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
