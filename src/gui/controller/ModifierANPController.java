/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.AnimalPerdu;
import gui.controller.ServiceAdminController;
import static gui.controller.ServiceAdminController.P;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudAnimalperdu;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierANPController implements Initializable {

    @FXML
    private TextField ida;
    @FXML
    private TextField lieu;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;
    @FXML
    private Label veterinaires;
    @FXML
    private DatePicker date;
    
        CrudAnimalperdu myTool = new CrudAnimalperdu();
    @FXML
    private Button ajouter;
       
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ida.setText(String.valueOf(ServiceAdminController.P.getId_animal()));
        Date date1 = ServiceAdminController.P.getDate_disparition();
        LocalDate date2 = date1.toLocalDate();
        date.setValue(date2);
        lieu.setText(ServiceAdminController.P.getLieu_disparition());
        etat.getItems().addAll("perdue","trouve");
        etat.setValue(((ServiceAdminController.P.getEtat1())));
        ida.setEditable(false);
        date.setEditable(false);
        lieu.setEditable(false);
    }    


    @FXML
    private void Menu(MouseEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage=new Stage();
               CrudAnimalperdu myTool = new CrudAnimalperdu();
               
               if ((ida.getText().equals("")) && (date.getValue().toString().compareTo("") == 0) && (lieu.getText().compareTo("") == 0)) {
            JOptionPane.showMessageDialog(null,"un ou plusieurs champs sont vides");

            } 
                  else {
                      
     
       //boolean etatt = Boolean.getBoolean(etat.getValue());
       String etatt= etat.getValue();
       switch (etatt)
       {
           case "perdue": 
               P.setEtat(false);
               break ;
           case "trouve":
               P.setEtat(true);
               break;
       }
       
                 
        myTool.Update(P);
        Alert alert1 = new Alert(AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Modification rÃ©ussite !");
           alert1.showAndWait();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                }
                      
                  }
        else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }
        }
    
    }
    

