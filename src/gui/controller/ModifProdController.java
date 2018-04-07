/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;
import entites.Produit;
import static gui.controller.ProduitAdminController.P;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudProduit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifProdController implements Initializable {
        
    @FXML
    private ComboBox<String> combobox;
    
    ObservableList<String> list = FXCollections.observableArrayList(
    "en attente","confirmer","annuler"
    );
    @FXML
    private HBox ev;
    @FXML
    private Button modifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.setItems(list);
        
    }    
    
    @FXML
    public void modifier(ActionEvent event) throws Exception
    {
    ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage=new Stage();
               CrudProduit myTool = new CrudProduit();
               
               if ( combobox.getValue() == null) {
                   System.out.println(combobox.getValue());
            JOptionPane.showMessageDialog(null,"le champ est vide");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/modifProd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } 
                  else {
                      
      String label=  P.getLabel();
                   
            Produit pr=new Produit(combobox.getValue(),label);
            System.out.println(combobox.getValue());
            System.out.println(P.getEtat());
            myTool.Update(pr);
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setTitle("I have a great message for you!");
            alert1.setHeaderText(null);
            alert1.setContentText("Modification réussite !");
            alert1.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ProduitAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                }
                      
                  }
        else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ProduitAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }
        }
    

    @FXML
    private void HomeClick(ActionEvent event) {
    }

    @FXML
    private void ClickStore(MouseEvent event) {
    }

    @FXML
    private void ClickEvenement(MouseEvent event) {
    }

    @FXML
    private void ClickEncheres(MouseEvent event) {
    }

    @FXML
    private void ClickServices(MouseEvent event) {
    }

    @FXML
    private void ClickVeterinaire(MouseEvent event) {
    }

    @FXML
    private void ClickProduit(MouseEvent event) {
    }
    
}
