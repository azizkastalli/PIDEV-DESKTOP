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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeAnimauxsdfController implements Initializable {

     public static AnimalSdf P= new AnimalSdf();
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
    private TableColumn<AnimalSdf, Integer> idca;
    @FXML
    private TableColumn<AnimalSdf, Date> date;
    @FXML
    private TableColumn<AnimalSdf, String> lieu;
    @FXML
    private TableColumn<AnimalSdf, String> sexe;
    @FXML
    private TableColumn<AnimalSdf, ImageView> img;
    @FXML
    private TableColumn<AnimalSdf, Integer> idc;
    private ObservableList <AnimalSdf> data;
    @FXML
    private Button Modifier;
    @FXML
    private Button adopter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudAnimalsdf myTool = new CrudAnimalsdf();
        AnimalSdf p = new AnimalSdf();
        data= FXCollections.observableArrayList();
        try {
            for(int i=0;i<myTool.SelectAll().size();i++)
            {
                p=(AnimalSdf) myTool.SelectAll().get(i);
                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalsdfAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idca.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_trouvaille"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_trouvaille"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        img.setCellValueFactory(new PropertyValueFactory<>("IV"));
        idc.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        tableA.setItems(null);
        tableA.setItems(data);
    }    

    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
         if (tableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            P=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
            //id.setText(String.valueOf(p.getId()));
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ModifierASDF.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une reclamation");
            }
    }


    @FXML
    private void Try(MouseEvent event) {
        if (tableA.getSelectionModel().getSelectedItem()!= null)
        {
            System.out.println(tableA.getSelectionModel().getSelectedItem().getId_client());
                 if ((loggduser.getId())!= (tableA.getSelectionModel().getSelectedItem().getId_client()) )
       {
           Modifier.setDisable(true);
           
       }
                 else 
                 {
                      Modifier.setDisable(false);
                      
                 }
            }
    }

    @FXML
    private void Adoter(ActionEvent event) {
        
    }
    
}
