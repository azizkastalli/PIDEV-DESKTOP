/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Rdv_Dresseur;
import static gui.controller.LoginController.loggduser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudRdv_dresseur;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeRdvController implements Initializable {

    public static Rdv_Dresseur R = new Rdv_Dresseur();
    @FXML
    private Label acceuil1;
    @FXML
    private Label services1;
    @FXML
    private Label produits1;
    @FXML
    private Label veterinaires1;
    
    @FXML
    private TableColumn<Rdv_Dresseur, String> idc;
    
    @FXML
    private TableColumn<Rdv_Dresseur, Integer> ida;
    @FXML
    private TableColumn<Rdv_Dresseur, Date> date;
    @FXML
    private TableColumn<Rdv_Dresseur, Boolean> etat;
    @FXML
    private TableColumn<Rdv_Dresseur, String> idd;
private ObservableList <Rdv_Dresseur> data;
@FXML
    private TableView<Rdv_Dresseur> TableA;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CrudRdv_dresseur myTool = new CrudRdv_dresseur();
       // Rdv_Dresseur p = new Rdv_Dresseur();
        data= FXCollections.observableArrayList();
        try {
            for(int i=0;i<myTool.SelectAll().size();i++)
            {
                R=(Rdv_Dresseur) myTool.SelectAll().get(i);
                data.add(R);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeRdvController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        ida.setCellValueFactory(new PropertyValueFactory<>("id_animal"));
        idd.setCellValueFactory(new PropertyValueFactory<>("id_dresseur"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat1"));
        idc.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        
        
        

       TableA.setItems(null);
       TableA.setItems(data);
    }    

    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }

    @FXML
    private void Ajout(ActionEvent event) throws IOException {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/RdvDresseur.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    Rdv_Dresseur A = new Rdv_Dresseur();
        CrudRdv_dresseur myTool = new CrudRdv_dresseur();

    @FXML
    private void Delete(ActionEvent event) throws IOException, SQLException {
        
                 if (TableA.getSelectionModel().getSelectedItem()!=null)
                 {
                      //id.setText(String.valueOf(p.getId()));
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){ 
            ((Node)event.getSource()).getScene().getWindow().hide();
            A=TableA.getItems().get(TableA.getSelectionModel().getSelectedIndex());
            System.out.println(A.getId());
            
                myTool.Delete(A);
               
            
            
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("I have a great message for you!");
        alert1.setHeaderText(null);
        alert1.setContentText("Suppression RÃ©ussite rÃ©ussite !");
        alert1.showAndWait();
            
           Stage stage=new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/ListeRdv.fxml"));
  
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
             ((Node)event.getSource()).getScene().getWindow().hide();
        }
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une reclamation");
            }
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
         if (TableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            R=TableA.getItems().get(TableA.getSelectionModel().getSelectedIndex());
            //id.setText(String.valueOf(p.getId()));
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ModifierRDV.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une reclamation");
            }
    }
    
}
