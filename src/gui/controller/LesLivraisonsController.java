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
public class LesLivraisonsController implements Initializable {

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
        for(int i=0;i<myTool.SelectAll().size();i++)
        { if(myTool.SelectAll().get(i).getEtat()==0)
        { p=(Commande) myTool.SelectAll().get(i);
            data.add(p);}
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
    private void livrer(MouseEvent event) throws IOException, SQLException {
        CrudCommande myTool = new CrudCommande();
        CrudLivraison myTool1 = new CrudLivraison();
        Livraison L = new Livraison();
        if (tableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            C=tableA.getSelectionModel().getSelectedItem();
            L.setEtat(0);
            L.setId_client(C.getId_client());
            L.setId_commande(C.getId());
            L.setId_livreur(loggduser.getId());
            myTool1.Create(L);
            C.setEtat(1);
            myTool.Update(C);
                System.out.println(C.getId());
            //id.setText(String.valueOf(p.getId()));
           
            
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/LesLivraisons.fxml"));
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
