/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import static gui.controller.AnimalsdfAdminController.P;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierASDFController implements Initializable {

    @FXML
    private TextField idc;
    @FXML
    private TextField idca;
    @FXML
    private TextField sexe;
    @FXML
    private TextField img;
    @FXML
    private TextField lieu;
    @FXML
    private Pane menubar;
    @FXML
    private Label acceuil1;
    @FXML
    private Label services1;
    @FXML
    private Label produits1;
    @FXML
    private Label veterinaires1;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idca.setText(String.valueOf(AnimalsdfAdminController.P.getId_categorie()));
        idc.setText(String.valueOf(AnimalsdfAdminController.P.getId_client()));
        Date date1 = AnimalsdfAdminController.P.getDate_trouvaille();
        LocalDate date2 = date1.toLocalDate();
        date.setValue(date2);
        lieu.setText(AnimalsdfAdminController.P.getLieu_trouvaille());
        sexe.setText(AnimalsdfAdminController.P.getSexe());
        img.setText(AnimalsdfAdminController.P.getNom_image());
       
    }    

    @FXML
    private void Modif(ActionEvent event) throws SQLException, IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage=new Stage();
               CrudAnimalsdf myTool = new CrudAnimalsdf();
               
               if ((idca.getText().equals("")) && (idc.getText().equals("")) && (date.getValue().toString().compareTo("") == 0) && (lieu.getText().compareTo("") == 0)&& (sexe.getText().compareTo("") == 0)&& (img.getText().compareTo("") == 0)) {
            JOptionPane.showMessageDialog(null,"un ou plusieurs champs sont vides");

            } 
                  else {
                      
        int id = Integer.parseInt(idca.getText());
        P.setId_categorie(id);
         int id2 = Integer.parseInt(idc.getText());
        P.setId_client(id2);
        LocalDate date1 = this.date.getValue();
        Date date2 = Date.valueOf(date1);
        P.setDate_trouvaille(date2);
        P.setLieu_trouvaille(lieu.getText());
        P.setSexe(sexe.getText());
        P.setNom_image(img.getText());
      
        myTool.Update(P);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Modification réussite !");
           alert1.showAndWait();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/AnimalsdfAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                }
                      
                  }
        else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AnimalsdfAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }
        }
    @FXML
    private void Menu(MouseEvent event) {
               MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }
    }

    
    

