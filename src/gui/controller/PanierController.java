/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import com.jfoenix.controls.JFXButton;
import entites.Commande;
import entites.Produit;
import entites.Lignecommande;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javax.management.Query.value;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class PanierController implements Initializable {

    //private List<Produits> lst_P ;
    // public static List<ArrayList> panier;
    //public static int nb_produits_panier;
    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> nomprod;
    @FXML
    private TableColumn<Produit, Float> prix;
    @FXML
    private TableColumn<Produit, String> type;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private Button commander;
    List<Commande> lst_com;
    CrudCommande serviceCommande = new CrudCommande();
    CrudProduit servP = new CrudProduit();
    Produit p;
    private TextField nbrArtcile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableMap<Produits,Integer> prod;
        List<Produit> lst_P = new ArrayList<>();
        for (int i = 0; i < StoreController.panier.size(); i++) {
            lst_P.add(servP.findById((Integer) StoreController.panier.get(i).get(0)));
        }
        System.out.println(lst_P.get(0).getLabel());
        ObservableList observableList = FXCollections.observableArrayList(lst_P);
        table.setItems(observableList);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomprod.setCellValueFactory(new PropertyValueFactory<>("label"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_nouv"));

        quantite.setEditable(true);

        // image.setCellValueFactory(new PropertyValueFactory<>("image"));
        //Dispo.setCellValueFactory(new PropertyValueFactory<>("Disponible"));
        type.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        //quantite.setCellValueFactory(TextFieldTableCell.forTableColumn());
        //Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    // TODO
    @FXML
    private void commander(ActionEvent event) throws ParseException, SQLException {
       /* lst_com = new ArrayList<>();

        Lignecommande lc = new Lignecommande();

        Commande cmd = new Commande();
        cmd.setId_client(1);
        serviceCommande.Create(cmd);
        lst_com.add(cmd);
        for (int i = 0; i < table.getItems().size(); i++) // if (table.getSelectionModel().getSelectedItem()!=null)
        {
            Commande c = serviceCommande.ConsulterListe_Commandes();
            lc.setId_commande(c.getId());

            System.out.println(cmd.getId());
            //System.err.println(c.getIdCommande());
            //for(int i=0;i<ListeProduits2Controller.panier.size();i++){

            p = table.getItems().get(i);
            int id = p.getId();

            int quantite = p.getQuantite();
            QuantiteController qc = new QuantiteController();
            serviceCommande.DecrementerStock(id,qc.qt);
            lc.setId_produit(p.getId());
            lc.setQte(1);
            serviceCommande.Create(c);
            // }

        }*/
    }

    private void NbrArtcile(MouseEvent event) {
        nbrArtcile.setText("");
    }

}

// System.out.println(panier.listIterator());